// src/main/java/com/example/musinssak/domain/order/entity/StockReservation.java
package com.example.musinssak.domain.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/** 재고 예약 스냅샷임 (30분 홀드용으로 씀) */
@Entity
@Table(name = "stock_reservations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class StockReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK임

    @Column(name = "order_id", nullable = false)
    private Long orderId; // 주문 id임

    @Column(name = "user_id", nullable = false)
    private Long userId; // 사용자 id임

    @Column(name = "product_option_id", nullable = false)
    private Long productOptionId; // 옵션 id임

    @Column(name = "quantity", nullable = false)
    private int quantity; // 예약 수량임

    @Column(name = "reservation_expires_at", nullable = false)
    private LocalDateTime reservationExpiresAt; // 만료 시각임
}
