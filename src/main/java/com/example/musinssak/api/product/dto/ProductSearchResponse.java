package com.example.musinssak.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSearchResponse { // 백엔드 -> 프론트 (응답 값)

    private Long id;             // 상품 ID
    private String brand;        // 브랜드명
    private String name;         // 상품명
    private int price;           // 할인된 가격
    private Integer originalPrice; // 원가 (nullable)
    private Integer discount;      // 할인율 (nullable)
    private String category;     // 카테고리명
    private String image;        // 썸네일 이미지 URL
}
