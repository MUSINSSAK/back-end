package com.example.musinssak.domain.cart.service;

/**
 * [설명]
 * 장바구니에 관련된 일을 “할 수 있다”는 약속(메뉴판)
 * 지금은 "담기" 한 가지만 필요
 */
public interface CartService {
    void addItem(Long userId, Long productOptionId, int quantity);
}
