package com.example.musinssak.api.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record ReviewCreateRequest(
        @NotNull
        Long productId,

        @NotNull
        @Min(value = 0, message = "별점은 0점 이상이어야 합니다.")
        @Max(value = 5, message = "별점은 5점 이하이어야 합니다.")
        Double rating,

        @NotBlank(message = "리뷰 내용은 비어 있을 수 없습니다.")
        String content,

        List<String> reviewImages
) {
}
