// src/main/java/com/example/musinssak/application/order/result/CreateOrderResult.java
package com.example.musinssak.application.order.result;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/** 주문 생성 결과 DTO임 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResult {
    private String orderNo;                 // 주문번호임
    private LocalDateTime reservationExpires; // 만료시각임
    private int totalProductAmount;         // 총 원가 합임
    private int discountAmount;             // 총 할인 합임
    private int deliveryFee;                // 배송비임
    private int finalAmount;                // 최종금액임
    @Builder.Default
    private List<Item> items = List.of();   // 아이템 목록임

    /** 응답용 아이템 한 줄임 */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private Long productId;       // 상품 id임
        private String productName;   // 상품명임
        private String brandName;     // 브랜드명임
        private String size;          // 사이즈임
        private int quantity;         // 수량임
        private int originalPrice;    // 원가임
        private int salePrice;        // 할인가임
    }
}
