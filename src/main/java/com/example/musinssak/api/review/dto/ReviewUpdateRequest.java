package com.example.musinssak.api.review.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record ReviewUpdateRequest(
        @NotBlank(message = "리뷰 내용은 비어 있을 수 없습니다.")
        String content,
        List<String> reviewImages
) {
}
