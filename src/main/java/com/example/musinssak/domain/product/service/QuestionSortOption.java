package com.example.musinssak.domain.product.service;

public enum QuestionSortOption {
    LATEST,        // 최신순
    PENDING_FIRST; // 답변대기 우선

    public static QuestionSortOption from(String value) {
        if (value == null) return LATEST;
        return switch (value.toLowerCase()) {
            case "latest" -> LATEST;
            case "pendingfirst" -> PENDING_FIRST;
            default -> LATEST;
        };
    }
}