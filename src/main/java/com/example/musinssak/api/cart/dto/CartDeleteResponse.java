// src/main/java/com/example/musinssak/api/cart/dto/CartDeleteResponse.java
package com.example.musinssak.api.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 지우고 나서 몇 개 지워졌고, 몇 개 남았는지 알려줌 */
@Getter
@AllArgsConstructor
public class CartDeleteResponse {
    private int deletedCount;   // 실제로 지워진 줄 개수임
    private int remainingItems; // 지우고 나서 장바구니에 남은 줄 개수임
}
