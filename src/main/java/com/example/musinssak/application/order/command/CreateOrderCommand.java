package com.example.musinssak.application.order.command;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 파사드 입력 모델임
 * - 컨트롤러 DTO와 분리함
 */
@Getter
@Builder
public class CreateOrderCommand {
    private final Long userId;            // 사용자 id임
    private final List<Long> cartItemIds; // 장바구니 아이템 id 목록임
}
