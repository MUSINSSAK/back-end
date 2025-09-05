// src/main/java/com/example/musinssak/api/cart/dto/CartSummaryDto.java
package com.example.musinssak.api.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartSummaryDto {
    // 금액 요약 담음
    private int selectedCount;        // 선택된 개수 담음
    private int totalProductAmount;   // 선택된 상품 세일가 합 담음
    private int totalCount;           // 전체 담긴 줄 수 담음
    private int totalPrice;           // 전체 원가 기준 합 담음
    private int discountAmount;       // 선택된 상품 할인 합 담음
    private int deliveryFee;          // 배송비 담음(0원 고정)
    private int finalAmount;          // 최종 결제금액 담음
}
