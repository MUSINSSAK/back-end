package com.example.musinssak.api.review.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MyReviewListResponse(
        List<WrittenReviewDto> writtenReviews,
        List<WritableReviewDto> writableReviews
) {
}
