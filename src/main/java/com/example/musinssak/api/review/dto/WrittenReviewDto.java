package com.example.musinssak.api.review.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record WrittenReviewDto(
        Long reviewId,
        Long productId,
        String productName,
        String thumbnailImageUrl,
        String purchaseDate,
        Double rating,
        String content,
        List<String> reviewImages
) {
}