package com.example.musinssak.api.review.dto;

import java.util.Locale;

public enum SortType {
    LATEST, HIGH, LOW;

    public static SortType from(String raw) {
        if (raw == null) return LATEST;
        String key = raw.trim().toLowerCase(Locale.ROOT);
        return switch (key) {
            case "latest" -> LATEST;
            case "high"   -> HIGH;
            case "low"    -> LOW;
            default       -> LATEST; // 잘못된 값은 latest로 처리
        };
    }
}