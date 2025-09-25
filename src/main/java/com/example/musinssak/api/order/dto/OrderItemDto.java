package com.example.musinssak.api.order.dto;

import lombok.Builder;

@Builder
public record OrderItemDto(
        String name,
        String option,
        String thumbnailUrl
) {
}