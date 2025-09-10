// src/main/java/com/example/musinssak/domain/order/service/StockReservationServiceImpl.java
package com.example.musinssak.domain.order.service;

import com.example.musinssak.domain.order.entity.StockReservation;
import com.example.musinssak.domain.order.repository.StockReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/** 재고 예약을 실제로 저장함 */
@Service
@RequiredArgsConstructor
public class StockReservationServiceImpl implements StockReservationService {

    private final StockReservationRepository stockReservationRepository;

    /** 예약을 DB에 저장함 */
    @Override
    @Transactional
    public ReservationPlan reserve(Long orderId, Long userId,
                                   List<ReservationPlan.ReservedItem> items,
                                   java.time.LocalDateTime expiresAt) {

        // 아이템마다 예약 행을 만듦
        for (ReservationPlan.ReservedItem it : items) {
            StockReservation sr = StockReservation.builder()
                    .orderId(orderId)
                    .userId(userId)
                    .productOptionId(it.getProductOptionId())
                    .quantity(it.getQuantity())
                    .reservationExpiresAt(expiresAt) // 컬럼명 reservation_expires_at 임
                    .build();
            stockReservationRepository.save(sr); // 저장함
        }

        // 그대로 계획을 돌려줌
        return ReservationPlan.builder()
                .orderId(orderId)
                .userId(userId)
                .expiresAt(expiresAt)
                .items(items)
                .build();
    }
}
