package com.example.musinssak.api.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/** 주문 생성 API의 요청/응답 DTO 묶음임 */
public class OrderDtos {

    /** 주문 생성 요청임 */
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateRequest {
        @Schema(description = "장바구니 아이템 id 목록임", example = "[1,2,3]")
        private List<Long> cartItemIds; // 선택 줄 id들임
    }

    /** 응답의 아이템 한 줄임 */
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Item {
        private Long productId;         // 상품 id임
        private Long productOptionId;   // 옵션 id임
        private int quantity;           // 수량임
        private int price;              // 원가 단가임
        private int discountPrice;      // 할인가 단가임
    }

    /** 주문 생성 응답임 */
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateResponse {
        private String orderId;                       // 주문번호임
        private LocalDateTime reservationExpiresAt;   // 예약 만료 시각임
        private int totalProductAmount;               // 총 원가 합임
        private int discountAmount;                   // 총 할인 합임임
        private int deliveryFee;                      // 배송비임
        private int finalAmount;                      // 최종 결제 금액임
        private List<Item> orderItems;                // 아이템 목록임
    }
}
