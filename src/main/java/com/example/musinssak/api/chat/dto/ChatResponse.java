package com.example.musinssak.api.chat.dto;

import java.util.List;

public record ChatResponse(
        String chatbotMessage,
        List<ProductInfo> recommendedProducts // 아래 ProductInfo 레코드 포함
) {
    public record ProductInfo(
            String productId,
            String productName,
            String brandName,
            Integer price,
            String imageUrl,
            String productLink
    ) {}
}