package com.example.musinssak.api.product.dto;

import com.example.musinssak.domain.product.service.QuestionSortOption;

public enum QuestionSort {
    LATEST,
    PENDING_FIRST;

    public static QuestionSort from(String raw) {
        if (raw == null) return LATEST;
        String v = raw.trim().toLowerCase();
        return switch (v) {
            case "latest" -> LATEST;
            case "pendingfirst" -> PENDING_FIRST;
            default -> LATEST;
        };
    }

    public QuestionSortOption toDomain() {
        return switch (this) {
            case LATEST -> QuestionSortOption.LATEST;
            case PENDING_FIRST -> QuestionSortOption.PENDING_FIRST;
        };
    }
}