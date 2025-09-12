// src/main/java/com/example/musinssak/application/order/OrderQueryServiceImpl.java
package com.example.musinssak.application.order;

import com.example.musinssak.api.order.dto.OrderItemsResponse;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.order.entity.Orders;
import com.example.musinssak.domain.order.repository.OrderItemRepository;
import com.example.musinssak.domain.order.repository.OrdersRepository;
import com.example.musinssak.domain.order.repository.view.OrderItemRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 주문 조회 구현체임(읽기 전용 트랜잭션 씀) */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrdersRepository ordersRepository;       // 주문 읽음
    private final OrderItemRepository orderItemRepository; // 아이템 조인 조회함

    @Override
    public OrderItemsResponse getOrderItems(Long orderId, Long userId) {
        // 1) 내 주문인지 확인함(없으면 403/400 중 택1)
        Orders order = ordersRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.FORBIDDEN)); // 내 주문 아님

        // 2) 아이템 조인해서 한 번에 가져옴
        List<OrderItemRow> rows = orderItemRepository.findRowsByOrderIdAndUserId(orderId, userId);

        // 3) 응답으로 변환함
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
