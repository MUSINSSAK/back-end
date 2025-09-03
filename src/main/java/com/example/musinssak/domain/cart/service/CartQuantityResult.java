// src/main/java/com/example/musinssak/domain/cart/service/CartQuantityResult.java
package com.example.musinssak.domain.cart.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 수량 변경 결과 모델임 */
@Getter
@AllArgsConstructor
public class CartQuantityResult {
    private Long cartItemId;     // 줄 id임
    private int oldQuantity;     // 기존 수량임
    private int newQuantity;     // 바뀐 수량임
    private int salePrice;       // 세일가임
    private int availableStock;  // 옵션 재고임
}
