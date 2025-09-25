package com.example.musinssak.api.order.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderHistoryResponse(
        List<OrderHistoryItemDto> orders,
        PaginationDto pagination
) {
}