// src/main/java/com/example/musinssak/application/order/OrderQueryServiceImpl.java
package com.example.musinssak.application.order;

import com.example.musinssak.api.order.dto.OrderItemsResponse;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.order.entity.OrderStatus;
import com.example.musinssak.domain.order.entity.Orders;
import com.example.musinssak.domain.order.repository.OrderItemRepository;
import com.example.musinssak.domain.order.repository.OrdersRepository;
import com.example.musinssak.domain.order.repository.view.OrderItemRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/** 주문 조회 구현체임 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderStatusUpdater orderStatusUpdater; // ★ 추가

    @Override
    public OrderItemsResponse getOrderItems(Long orderId, Long userId) {
        // 1) 내 주문인지 확인
        Orders order = ordersRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN));

        // 2) 만료 즉시 반영(읽기 트랜잭션이지만 내부에서 REQUIRES_NEW로 상태만 커밋)
        if (order.getStatus() == OrderStatus.CREATED
                && order.getExpiredAt() != null
                && order.getExpiredAt().isBefore(LocalDateTime.now())) {
            orderStatusUpdater.markPaymentExpiredNow(order.getId()); // DB에 바로 반영
            throw new BusinessException(ErrorCode.ORDER_TIME_EXPIRED);
        }

        // 3) 아이템 조인 조회
        List<OrderItemRow> rows = orderItemRepository.findRowsByOrderIdAndUserId(orderId, userId);

        // 4) 응답
        return OrderItemsResponse.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .status(order.getStatus().name())
                .reservationExpiresAt(order.getExpiredAt())
                .totalProductAmount(order.getTotalProductPrice())
                .discountAmount(order.getTotalDiscountPrice())
                .deliveryFee(order.getDeliveryFee())
                .finalAmount(order.getFinalPaymentPrice())
                .items(rows.stream().map(r ->
                        OrderItemsResponse.Item.builder()
                                .productId(r.getProductId())
                                .productName(r.getProductName())
                                .brandName(r.getBrandName())
                                .size(r.getSize())
                                .quantity(r.getQuantity())
                                .originalPrice(r.getOriginalPrice())
                                .salePrice(r.getSalePrice())
                                .build()
                ).toList())
                .build();
    }
}
