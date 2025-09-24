package com.example.musinssak.api.review.dto;

import lombok.Builder;

@Builder
public record WritableReviewDto(
        Long productId,
        String productName,
        String thumbnailImageUrl,
        String purchaseDate
) {
}