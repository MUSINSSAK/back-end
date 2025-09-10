package com.example.musinssak.api.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 주문 생성 응답 바디임
 * - 1~2단계에서는 주문번호/만료시각을 내려줌
 * - 금액/아이템은 다음 단계에서 채움
 */
@Getter
@Builder
public class OrderCreateResponse {

    private String orderId;                      // 주문번호임
    private LocalDateTime reservationExpiresAt;  // 예약 만료 시각임(지금 +30분)

    // 금액 정보임(다음 단계에서 채움)
    private Integer totalProductAmount;
    private Integer discountAmount;
    private Integer deliveryFee;
    private Integer finalAmount;

    // 주문 아이템 스냅샷임(다음 단계에서 채움)
    private List<Item> orderItems;

    @Getter
    @Builder
    public static class Item {
        private Long productId;
        private Long productOptionId;
        private String size;
        private Integer quantity;
        private Integer price;          // 원가 단가임
        private Integer discountPrice;  // 할인 단가임
    }
}
