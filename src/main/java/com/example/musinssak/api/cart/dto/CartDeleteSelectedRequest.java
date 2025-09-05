// src/main/java/com/example/musinssak/api/cart/dto/CartDeleteSelectedRequest.java
package com.example.musinssak.api.cart.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/** 여러 개를 한 번에 지울 때 씀 */
@Getter
@NoArgsConstructor
public class CartDeleteSelectedRequest {
    // 지울 장바구니 아이템 id 목록임
    @NotEmpty(message = "삭제할 장바구니 아이템 ID 목록이 비어있으면 안됨")
    private List<Long> cartItemIds;
}
