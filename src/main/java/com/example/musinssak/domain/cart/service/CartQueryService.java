// src/main/java/com/example/musinssak/domain/cart/service/CartQueryService.java
package com.example.musinssak.domain.cart.service;

import com.example.musinssak.api.cart.dto.CartGetResponse;

public interface CartQueryService {
    // 장바구니 조회를 함
    CartGetResponse getCart(Long userId);
}
