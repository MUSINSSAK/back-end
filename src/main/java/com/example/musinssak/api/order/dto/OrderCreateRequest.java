package com.example.musinssak.api.order.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 주문 생성 요청 바디임
 * - 장바구니에서 선택된 아이템 id 목록을 받음
 * - 비어 있으면 안됨
 */
@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    @NotEmpty(message = "cartItemIds는 비어있을 수 없음")
    private List<Long> cartItemIds; // 선택된 장바구니 아이템 id 모음임
}
