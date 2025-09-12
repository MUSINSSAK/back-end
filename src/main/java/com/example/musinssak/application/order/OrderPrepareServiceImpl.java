// src/main/java/com/example/musinssak/application/order/OrderPrepareServiceImpl.java
package com.example.musinssak.application.order;

import com.example.musinssak.api.order.dto.OrderUpdateRequest;
import com.example.musinssak.api.order.dto.OrderUpdateResponse;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.order.entity.*;
import com.example.musinssak.domain.order.repository.*;
import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.product.entity.ProductOption;
import com.example.musinssak.domain.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/** 결제 전 단계에서 주문자/배송지/금액을 갱신함 */
@Service
@RequiredArgsConstructor
public class OrderPrepareServiceImpl implements OrderPrepareService {

    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderDeliveryRepository orderDeliveryRepository;
    private final ProductOptionRepository productOptionRepository;

    /** 주문자/배송지 갱신 + 현재 가격으로 금액 재계산함 */
    @Override
    @Transactional
    public OrderUpdateResponse updateAndRecalculate(Long orderId, Long userId, OrderUpdateRequest req) {

        // 1) 주문 로드/검증함
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND)); // 주문 없음임

        if (!order.getUserId().equals(userId)) {
            throw new BusinessException(ErrorCode.AUTH_FORBIDDEN); // 내 주문만 가능함
        }
        if (order.getStatus() != OrderStatus.CREATED) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST); // 진행 불가 상태임
        }
        if (order.getExpiredAt() != null && order.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new BusinessException(ErrorCode.ORDER_TIME_EXPIRED); // 만료됨
        }

        // 2) 주문자 정보 갱신함(있을 때만)
        if (req.getOrdererInfo() != null) {
            var oi = req.getOrdererInfo();
            order.applyOrderer(oi.getName(), oi.getEmail(), oi.getPhone()); // 주문자 적용함
        }

        // 3) 배송지 upsert함(있을 때만)
        if (req.getDeliveryInfo() != null) {
            var di = req.getDeliveryInfo();

            // 기존 있으면 사용, 없으면 새로 생성함
            OrderDelivery delivery = orderDeliveryRepository.findByOrder_Id(order.getId())
                    .orElseGet(() -> OrderDelivery.builder().order(order).build());

            // label은 DB에 없어서 무시됨(오버로드로 처리함)
            delivery.apply(
                    di.getLabel(),
                    di.getRecipient(),
                    di.getPhone(),
                    di.getAddress(),
                    di.getDetailAddress(),
                    di.getPostalCode(),
                    di.getDeliveryRequest()
            );

            // 연관 묶음 보장함
            delivery.bindOrder(order);

            // 저장함
            orderDeliveryRepository.save(delivery);
        }

        // 4) 금액 재계산함(현재 상품가격 기준)
        List<OrderItem> items = orderItemRepository.findByOrder_Id(order.getId());

        int totalProduct = 0;  // 원가 합임
        int totalDiscount = 0; // 할인 합임
        for (OrderItem it : items) {
            ProductOption po = productOptionRepository.findById(it.getProductOptionId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_OPTION_NOT_FOUND)); // 옵션 없음임

            Product p = po.getProduct();
            int price = p.getOriginalPrice();                 // 현재 원가임
            Integer discounted = p.getDiscountedPrice();      // 현재 할인가임(없을 수 있음)
            int salePrice = (discounted != null) ? discounted : price;

            totalProduct += price * it.getQuantity();
            totalDiscount += (price - salePrice) * it.getQuantity();
        }
        int deliveryFee = 0; // 지금은 0원임
        int finalAmount = totalProduct - totalDiscount + deliveryFee;

        // 주문 금액 적용함
        order.applyAmounts(totalProduct, totalDiscount, deliveryFee, finalAmount);

        // 5) 응답 만듦
        String remaining = formatRemaining(order.getExpiredAt());

        OrderUpdateResponse.PriceBreakdown pb = OrderUpdateResponse.PriceBreakdown.builder()
                .originalProductAmount(totalProduct)       // 상품 원가 합임
                .productDiscount(totalDiscount)            // 상품 할인 합임
                .productAmount(totalProduct - totalDiscount)
                .couponDiscount(0)                         // 다음 단계에서 반영함
                .pointsUsed(0)                             // 다음 단계에서 반영함
                .totalDiscount(totalDiscount)              // 현재는 상품할인만 포함함
                .deliveryFee(deliveryFee)
                .finalAmount(finalAmount)
                .build();

        // 저장된 배송지 echo용(없으면 null임)
        OrderDelivery savedDelivery = orderDeliveryRepository.findByOrder_Id(order.getId()).orElse(null);
        OrderUpdateRequest.DeliveryInfo deliveryInfo = (savedDelivery == null) ? null :
                OrderUpdateRequest.DeliveryInfo.builder()
                        .label(null) // DB에 없음 → null로 둠
                        .recipient(savedDelivery.getRecipient())
                        .phone(savedDelivery.getPhone())
                        .address(savedDelivery.getAddress())
                        .detailAddress(savedDelivery.getDetailAddress())
                        .postalCode(savedDelivery.getPostalCode())
                        .deliveryRequest(savedDelivery.getDeliveryRequest())
                        .build();

        return OrderUpdateResponse.builder()
                .orderId(order.getOrderNumber())
                .status(order.getStatus().name())
                .reservationExpiresAt(order.getExpiredAt() == null ? null : order.getExpiredAt().toString())
                .remainingTime(remaining)
                .priceBreakdown(pb)
                .deliveryInfo(deliveryInfo)
                .build();
    }

    /** 남은 시간을 mm:ss로 만듦 */
    private String formatRemaining(LocalDateTime expiresAt) {
        if (expiresAt == null) return null;
        long sec = Math.max(0, Duration.between(LocalDateTime.now(), expiresAt).getSeconds());
        long mm = sec / 60;
        long ss = sec % 60;
        return String.format("%02d:%02d", mm, ss);
    }
}
