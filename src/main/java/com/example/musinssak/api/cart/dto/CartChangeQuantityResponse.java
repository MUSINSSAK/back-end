// src/main/java/com/example/musinssak/api/cart/dto/CartChangeQuantityResponse.java
package com.example.musinssak.api.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 수량 변경 응답 DTO임 */
@Getter
@AllArgsConstructor
public class CartChangeQuantityResponse {
    private Long cartItemId;     // 줄 id임
    private int oldQuantity;     // 기존 수량임
    private int newQuantity;     // 바뀐 수량임
    private int itemTotalPrice;  // 세일가 × 수량 합임
    private int availableStock;  // 옵션 재고임
    private boolean canIncrease; // + 가능 여부임
    private boolean canDecrease; // - 가능 여부임
}
