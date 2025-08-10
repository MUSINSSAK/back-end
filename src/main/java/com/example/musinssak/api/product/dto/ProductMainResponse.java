package com.example.musinssak.api.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 리액트 변수명과 일치 시키기
@Getter
@AllArgsConstructor
public class ProductMainResponse {

    private Long id;                // 상품 ID
    private String name;            // Product.name → 프론트와 일치
    private String brand;           // Brand.name
    private String image;           // Product.thumbnailImageUrl
    private int price;              // Product.discountedPrice
    private int originalPrice;      // 그대로 사용
    private int discount;           // Product.discountRate
}