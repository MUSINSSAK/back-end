package com.example.musinssak.api.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 장바구니 담기 요청 DTO
 * - productOptionId: 어떤 옵션(색상/사이즈 등)을 담을지
 * - quantity: 몇 개 담을지 (최소 1개)
 */
@Getter @Setter
public class CartAddItemRequest {

    @NotNull
    private Long productOptionId;

    @Min(1)
    private int quantity;
}
