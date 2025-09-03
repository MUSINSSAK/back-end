
package com.example.musinssak.api.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartGetResponse {
    // 조회 결과 전체를 담음
    private Long userId;               // 유저 id 담음
    private List<CartItemDto> cartItems; // 아이템 리스트 담음
    private int selectedCount;         // 선택된 개수 담음
    private int totalProductAmount;    // 선택 세일가 합 담음
    private int totalCount;            // 전체 개수 담음
    private int totalPrice;            // 전체 원가 합 담음
    private int discountAmount;        // 선택 할인 합 담음
    private int deliveryFee;           // 배송비 담음
    private int finalAmount;           // 최종 결제금액 담음
}
