// src/main/java/com/example/musinssak/application/order/OrderQueryService.java
package com.example.musinssak.application.order;

import com.example.musinssak.api.order.dto.OrderItemsResponse;

/** 주문 조회 유스케이스임(읽기 전용임) */
public interface OrderQueryService {
    /** 내 주문 아이템 목록/합계 내려줌 */
    OrderItemsResponse getOrderItems(Long orderId, Long userId);

    /** 주문번호로 내 주문 아이템 목록/합계 내려줌 */
    OrderItemsResponse getOrderItemsByNumber(String orderNumber, Long userId);
}
