package com.example.musinssak.api.chat.dto;

import java.util.List;

public record AiServerRequest(
        String query,
        UserInfo userInfo // 아래 UserInfo 레코드 포함
) {
    public record UserInfo(
            String userId,
            Integer height,
            Integer weight,
            String gender,
            List<String> recentPurchases,
            List<String> wishlist
    ) {}
}