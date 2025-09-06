package com.example.musinssak.api.review.dto;

import lombok.Builder;

@Builder
public record ReviewItem(
        Long id,
        Integer rating,
        String author,     // 마스킹된 닉네임
        Boolean hasPhoto,
        String content,
        String createdAt   // yyyy-MM-dd
) {}
