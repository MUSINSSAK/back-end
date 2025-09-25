package com.example.musinssak.api.order.dto;

import lombok.Builder;

@Builder
public record PaginationDto(
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}