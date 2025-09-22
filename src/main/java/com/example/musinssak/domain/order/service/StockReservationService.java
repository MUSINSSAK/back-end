// src/main/java/com/example/musinssak/domain/order/service/StockReservationService.java
package com.example.musinssak.domain.order.service;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/** 재고 예약을 다루는 서비스 규칙임 */
public interface StockReservationService {

    /** 예약 계획 DTO임 */
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class ReservationPlan {
        private Long orderId;              // 주문 id임
        private Long userId;               // 유저 id임
        private LocalDateTime expiresAt;   // 만료 시각임
        @Builder.Default
        private List<ReservedItem> items = List.of(); // 예약 아이템 목록임

        /** 예약 아이템 한 줄임 */
        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ReservedItem {
            private Long productOptionId;  // 옵션 id임
            private int quantity;          // 수량임
        }
    }

    /** 재고 예약을 생성함 */
    ReservationPlan reserve(Long orderId,
                            Long userId,
                            List<ReservationPlan.ReservedItem> items,
                            LocalDateTime expiresAt);
}
