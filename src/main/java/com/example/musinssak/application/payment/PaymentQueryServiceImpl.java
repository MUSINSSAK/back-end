// src/main/java/com/example/musinssak/application/payment/PaymentQueryServiceImpl.java
package com.example.musinssak.application.payment;

import com.example.musinssak.api.payment.dto.PaymentInfoResponse;
import com.example.musinssak.application.order.OrderStatusUpdater;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.order.entity.OrderDelivery;
import com.example.musinssak.domain.order.entity.OrderStatus;
import com.example.musinssak.domain.order.entity.Orders;
import com.example.musinssak.domain.order.repository.OrderDeliveryRepository;
import com.example.musinssak.domain.order.repository.OrderItemRepository;
import com.example.musinssak.domain.order.repository.OrdersRepository;
import com.example.musinssak.domain.order.repository.view.OrderItemRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 결제 조회 서비스 구현체임
 * 결제 정보 조회와 관련된 모든 비즈니스 로직을 처리함
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentQueryServiceImpl implements PaymentQueryService {

    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderDeliveryRepository orderDeliveryRepository;
    private final OrderStatusUpdater orderStatusUpdater;

    @Override
    public PaymentInfoResponse getPaymentInfo(String orderId, Long userId) {
        // 1) 주문 존재 여부 및 권한 검증함
        Orders order = ordersRepository.findByOrderNumberAndUserId(orderId, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN));

        // 2) 주문 상태 검증 및 만료 처리함
        validateOrderStatus(order);

        // 3) 주문 상품 목록을 조회함
        List<OrderItemRow> orderItemRows = orderItemRepository.findRowsByOrderIdAndUserId(order.getId(), userId);

        // 4) 배송 정보를 조회함 (있는 경우만)
        OrderDelivery delivery = orderDeliveryRepository.findByOrder_Id(order.getId()).orElse(null);

        // 5) 시간 관련 정보를 계산함
        TimeCalculationResult timeResult = calculateTimeInfo(order.getExpiredAt());

        // 6) 응답 DTO를 구성해서 반환함
        return buildPaymentInfoResponse(order, orderItemRows, delivery, timeResult);
    }

    /**
     * 주문 상태를 검증하고 만료 처리를 수행함
     * - CREATED, PAYMENT_PENDING 상태만 결제 가능함
     * - 만료된 주문은 상태 업데이트 후 예외 발생함
     */
    private void validateOrderStatus(Orders order) {
        LocalDateTime now = LocalDateTime.now();

        // 만료 시간 체크 및 상태 업데이트
        if (order.getExpiredAt() != null && order.getExpiredAt().isBefore(now)) {
            // 만료된 주문의 상태를 업데이트함
            if (order.getStatus() == OrderStatus.CREATED || order.getStatus() == OrderStatus.PAYMENT_PENDING) {
                orderStatusUpdater.markPaymentExpiredNow(order.getId());
            }
            throw new BusinessException(ErrorCode.ORDER_TIME_EXPIRED);
        }

        // 결제 가능한 상태인지 확인함
        if (order.getStatus() != OrderStatus.CREATED && order.getStatus() != OrderStatus.PAYMENT_PENDING) {
            throw new BusinessException(ErrorCode.INVALID_ORDER_STATUS);
        }
    }

    /**
     * 시간 관련 정보를 계산함
     * - 남은 시간을 MM:SS 형태로 변환함
     * - 10분 미만 시 알림 정보를 생성함
     */
    private TimeCalculationResult calculateTimeInfo(LocalDateTime expiredAt) {
        if (expiredAt == null) {
            return new TimeCalculationResult("00:00", null);
        }

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, expiredAt);

        if (duration.isNegative()) {
            return new TimeCalculationResult("00:00", null);
        }

        // 남은 시간을 분:초 형태로 변환함
        long totalMinutes = duration.toMinutes();
        long seconds = duration.getSeconds() % 60;
        String remainingTime = String.format("%02d:%02d", totalMinutes, seconds);

        // 10분 미만일 때 알림 정보를 생성함
        PaymentInfoResponse.TimeAlert timeAlert = null;
        if (totalMinutes < 10) {
            String alertType = totalMinutes < 5 ? "DANGER" : "WARNING";
            String alertMessage = totalMinutes < 5
                ? "주문 시간이 5분 미만 남았습니다. 즉시 결제해주세요!"
                : "주문 시간이 10분 미만 남았습니다. 서둘러 결제해주세요!";

            timeAlert = PaymentInfoResponse.TimeAlert.builder()
                    .shouldShowAlert(true)
                    .alertType(alertType)
                    .alertMessage(alertMessage)
                    .remainingMinutes((int) totalMinutes)
                    .build();
        }

        return new TimeCalculationResult(remainingTime, timeAlert);
    }

    /**
     * PaymentInfoResponse를 구성함
     * - 주문 정보, 상품 목록, 배송 정보, 시간 정보를 종합함
     * - 금액 계산을 수행함
     */
    private PaymentInfoResponse buildPaymentInfoResponse(
            Orders order,
            List<OrderItemRow> orderItemRows,
            OrderDelivery delivery,
            TimeCalculationResult timeResult) {

        // 주문 상품 목록을 변환함
        List<PaymentInfoResponse.OrderItem> orderItems = orderItemRows.stream()
                .map(this::convertToOrderItem)
                .toList();

        // 결제 금액 요약을 계산함
        PaymentInfoResponse.PaymentSummary paymentSummary = calculatePaymentSummary(order, orderItemRows);

        // 배송 정보를 변환함 (있는 경우만)
        PaymentInfoResponse.DeliveryInfo deliveryInfo = delivery != null
                ? convertToDeliveryInfo(delivery)
                : null;

        return PaymentInfoResponse.builder()
                .orderPk(order.getId())
                .orderId(order.getOrderNumber())
                .orderItems(orderItems)
                .paymentSummary(paymentSummary)
                .deliveryInfo(deliveryInfo)
                .remainingTime(timeResult.remainingTime())
                .timeAlert(timeResult.timeAlert())
                .build();
    }

    /**
     * OrderItemRow를 PaymentInfoResponse.OrderItem으로 변환함
     * - 개별 상품의 금액 계산을 수행함
     */
    private PaymentInfoResponse.OrderItem convertToOrderItem(OrderItemRow row) {
        int totalOriginalPrice = row.getOriginalPrice() * row.getQuantity();
        int totalDiscountedPrice = row.getSalePrice() * row.getQuantity();
        boolean hasDiscount = row.getOriginalPrice() != row.getSalePrice();

        return PaymentInfoResponse.OrderItem.builder()
                .productId(row.getProductId())
                .productName(row.getProductName())
                .brandName(row.getBrandName())
                .thumbnailImageUrl(row.getImageUrl())
                .size(row.getSize())
                .quantity(row.getQuantity())
                .originalPrice(row.getOriginalPrice())
                .discountedPrice(row.getSalePrice())
                .hasDiscount(hasDiscount)
                .totalOriginalPrice(totalOriginalPrice)
                .totalDiscountedPrice(totalDiscountedPrice)
                .build();
    }

    /**
     * 결제 금액 요약을 계산함
     * - 현재는 기본적인 계산만 수행함
     * - 추후 쿠폰, 적립금 등의 로직 추가 예정함
     */
    private PaymentInfoResponse.PaymentSummary calculatePaymentSummary(Orders order, List<OrderItemRow> orderItemRows) {
        // TODO: 쿠폰, 적립금 계산 로직 추가 필요함
        int couponDiscount = 0;
        int pointsUsed = 0;

        return PaymentInfoResponse.PaymentSummary.builder()
                .originalProductAmount(order.getTotalProductPrice())
                .productDiscount(order.getTotalDiscountPrice())
                .productAmount(order.getTotalProductPrice() - order.getTotalDiscountPrice())
                .couponDiscount(couponDiscount)
                .pointsUsed(pointsUsed)
                .totalDiscount(order.getTotalDiscountPrice() + couponDiscount + pointsUsed)
                .deliveryFee(order.getDeliveryFee())
                .finalAmount(order.getFinalPaymentPrice())
                .build();
    }

    /**
     * OrderDelivery를 PaymentInfoResponse.DeliveryInfo로 변환함
     */
    private PaymentInfoResponse.DeliveryInfo convertToDeliveryInfo(OrderDelivery delivery) {
        return PaymentInfoResponse.DeliveryInfo.builder()
                .recipient(delivery.getRecipient())
                .phone(delivery.getPhone())
                .address(delivery.getAddress())
                .detailAddress(delivery.getDetailAddress())
                .postalCode(delivery.getPostalCode())
                .deliveryRequest(delivery.getDeliveryRequest())
                .build();
    }

    /**
     * 시간 계산 결과를 담는 내부 레코드임
     */
    private record TimeCalculationResult(
            String remainingTime,
            PaymentInfoResponse.TimeAlert timeAlert
    ) {}
}