package com.example.musinssak.domain.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/** 재고 예약 엔티티임(스냅샷 아님, 임시 예약임) */
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

    @Column(name = "order_id")
    private Long orderId; // 결제 완료 후 주문 id로 연결됨(지금은 null 가능함)

    @Column(name = "user_id", nullable = false)
    private Long userId; // 예약자 유저 id임

    @Column(name = "product_option_id", nullable = false)
    private Long productOptionId; // 옵션 id임

    @Column(name = "quantity", nullable = false)
    private int quantity; // 예약 수량임

    @Column(name = "reservation_expires_at", nullable = false)
    private LocalDateTime reservationExpiresAt; // 예약 만료시각임
}
