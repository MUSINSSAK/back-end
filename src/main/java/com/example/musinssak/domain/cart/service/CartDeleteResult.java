// src/main/java/com/example/musinssak/domain/cart/service/CartDeleteResult.java
package com.example.musinssak.domain.cart.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 지우기 작업의 결과를 담는 모델임 */
@Getter
@AllArgsConstructor
public class CartDeleteResult {
    private int deletedCount;    // 몇 개 지워졌는지임
    private int remainingItems;  // 장바구니에 몇 개 줄이 남았는지임
}
