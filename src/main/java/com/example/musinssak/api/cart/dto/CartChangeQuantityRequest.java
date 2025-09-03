// src/main/java/com/example/musinssak/api/cart/dto/CartChangeQuantityRequest.java
package com.example.musinssak.api.cart.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/** 수량 변경 요청 DTO임 */
@Getter @Setter
public class CartChangeQuantityRequest {
    @Min(1)
    private int quantity;   // 최종 수량임
    private String action;  // optional, "increase"/"decrease" 구분용임
}
