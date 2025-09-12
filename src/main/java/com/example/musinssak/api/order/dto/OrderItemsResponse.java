// src/main/java/com/example/musinssak/api/order/dto/OrderItemsResponse.java
package com.example.musinssak.api.order.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/** 주문상품 조회 응답 DTO임 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsResponse {

    private Long orderId;                   // 주문 id임
    private String orderNumber;             // 주문번호임
    private String status;                  // 주문상태임
    private LocalDateTime reservationExpiresAt; // 예약만료시각임(타이머에 씀)

    private int totalProductAmount;         // 총 원가합임
    private int discountAmount;             // 총 할인합임
    private int deliveryFee;                // 배송비임
    private int finalAmount;                // 최종결제금액임

    private List<Item> items;               // 주문상품 목록임

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private Long productId;     // 상품 id임
        private String productName; // 상품명임
        private String brandName;   // 브랜드명임
        private String size;        // 옵션 사이즈임
        private int quantity;       // 수량임
        private int originalPrice;  // 원가 단가임
        private int salePrice;      // 할인가 단가임
    }
}
