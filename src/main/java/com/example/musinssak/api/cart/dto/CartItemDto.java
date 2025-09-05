// src/main/java/com/example/musinssak/api/cart/dto/CartItemDto.java
package com.example.musinssak.api.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartItemDto {
    // 한 줄에 대한 정보 담음
    private Long cartItemId;       // 장바구니아이템 id 담음
    private Long productId;        // 상품 id 담음
    private String productName;    // 상품명 담음
    private String brandName;      // 브랜드명 담음
    private String productImageUrl;// 썸네일 주소 담음
    private String size;           // 옵션(사이즈) 담음
    private int quantity;          // 수량 담음
    private int originalPrice;     // 원가 담음
    private int salePrice;         // 세일가 담음
    private int discountRate;      // 할인율 담음
    private boolean selected;      // 선택 여부 담음
    private int stock;             // 재고 담음
}
