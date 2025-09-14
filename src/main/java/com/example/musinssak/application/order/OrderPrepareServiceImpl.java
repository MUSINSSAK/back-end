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
    private final OrderStatusUpdater orderStatusUpdater; // ★ 추가

    @Override
    @Transactional
    public OrderUpdateResponse updateAndRecalculate(Long orderId, Long userId, OrderUpdateRequest req) {

        // 1) 주문 로드/검증
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));
        if (!order.getUserId().equals(userId)) {
            throw new BusinessException(ErrorCode.AUTH_FORBIDDEN);
        }

        // 1-1) 만료 시 즉시 상태 반영
        if (order.getStatus() == OrderStatus.CREATED
                && order.getExpiredAt() != null
                && order.getExpiredAt().isBefore(LocalDateTime.now())) {
            // 상태만 먼저 커밋
            orderStatusUpdater.markPaymentExpiredNow(order.getId());
            // 나머지 변경은 안 하도록 예외
            throw new BusinessException(ErrorCode.ORDER_TIME_EXPIRED);
        }

        // 2) 주문자 정보 갱신(있으면)
        if (req.getOrdererInfo() != null) {
            var oi = req.getOrdererInfo();
            order.applyOrderer(oi.getName(), oi.getEmail(), oi.getPhone());
        }

        // 3) 배송지 upsert(있으면)
        if (req.getDeliveryInfo() != null) {
            var di = req.getDeliveryInfo();
            OrderDelivery delivery = orderDeliveryRepository.findByOrder_Id(order.getId())
                    .orElseGet(() -> OrderDelivery.builder().order(order).build());
            delivery.apply(
                    di.getLabel(), // DB엔 없음 → 무시
                    di.getRecipient(),
                    di.getPhone(),
                    di.getAddress(),
                    di.getDetailAddress(),
                    di.getPostalCode(),
                    di.getDeliveryRequest()
            );
            delivery.bindOrder(order);
            orderDeliveryRepository.save(delivery);
        }

        // 4) 금액 재계산(현재 상품 가격 기준)
        List<OrderItem> items = orderItemRepository.findByOrder_Id(order.getId());
        int totalProduct = 0;
        int totalDiscount = 0;
        for (OrderItem it : items) {
            ProductOption po = productOptionRepository.findById(it.getProductOptionId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_OPTION_NOT_FOUND));
            Product p = po.getProduct();
            int price = p.getOriginalPrice();
            Integer discounted = p.getDiscountedPrice();
            int salePrice = (discounted != null) ? discounted : price;

            totalProduct += price * it.getQuantity();
            totalDiscount += (price - salePrice) * it.getQuantity();
        }
        int deliveryFee = 0;
        int finalAmount = totalProduct - totalDiscount + deliveryFee;
        order.applyAmounts(totalProduct, totalDiscount, deliveryFee, finalAmount);

        // 5) 응답
        String remaining = formatRemaining(order.getExpiredAt());

        OrderUpdateResponse.PriceBreakdown pb = OrderUpdateResponse.PriceBreakdown.builder()
                .originalProductAmount(totalProduct)
                .productDiscount(totalDiscount)
                .productAmount(totalProduct - totalDiscount)
                .couponDiscount(0)
                .pointsUsed(0)
                .totalDiscount(totalDiscount)
                .deliveryFee(deliveryFee)
                .finalAmount(finalAmount)
                .build();

        OrderDelivery savedDelivery = orderDeliveryRepository.findByOrder_Id(order.getId()).orElse(null);
        OrderUpdateRequest.DeliveryInfo deliveryInfo = (savedDelivery == null) ? null :
                OrderUpdateRequest.DeliveryInfo.builder()
                        .label(null)
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

    private String formatRemaining(LocalDateTime expiresAt) {
        if (expiresAt == null) return null;
        long sec = Math.max(0, Duration.between(LocalDateTime.now(), expiresAt).getSeconds());
        long mm = sec / 60;
        long ss = sec % 60;
        return String.format("%02d:%02d", mm, ss);
    }
}
