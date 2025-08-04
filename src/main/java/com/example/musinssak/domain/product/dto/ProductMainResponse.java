package com.example.musinssak.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductMainResponse {
    private Long productId;
    private String productName;
    private String brandName;
    private String thumbnailImageUrl;
    private int originalPrice;
    private int discountedPrice;
    private int discountRate;
}
