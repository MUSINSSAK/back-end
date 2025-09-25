package com.example.musinssak.application.order;

import com.example.musinssak.api.order.dto.OrderHistoryResponse; // [추가]
import com.example.musinssak.api.order.dto.OrderItemsResponse;

public interface OrderQueryService {
    OrderItemsResponse getOrderItems(Long orderId, Long userId);
    OrderItemsResponse getOrderItemsByNumber(String orderNumber, Long userId);

    // [추가] 주문 내역 조회 메소드
    OrderHistoryResponse getMyOrderHistory(Long userId, String period, String status, int page, int size);
}