package com.example.musinssak.api.order.dto;

import com.example.musinssak.domain.order.entity.OrderStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderHistoryItemDto(
        String orderDate,
        String orderNumber,
        List<OrderItemDto> items,
        int totalAmount,
        OrderStatus orderStatus
) {
}