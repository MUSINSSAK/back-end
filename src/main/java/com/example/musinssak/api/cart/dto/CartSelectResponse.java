package com.example.musinssak.api.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartSelectResponse {
    /** 현재 선택된 장바구니 아이템 수 (productOption 묶음 기준) */
    private int selectedCount;

    /** 현재 선택된 상품들의 총 결제 금액 (discountedPrice * quantity 합) */
    private int selectedTotalPrice;
}