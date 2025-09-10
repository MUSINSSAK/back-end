// src/main/java/com/example/musinssak/domain/order/repository/StockReservationRepository.java
package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.StockReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface StockReservationRepository extends JpaRepository<StockReservation, Long> {

    /** 아직 만료 안 된 예약 수량 합을 구함 */
    @Query("""
        select coalesce(sum(sr.quantity), 0)
          from StockReservation sr
         where sr.productOptionId = :optionId
           and sr.reservationExpiresAt > :now
    """)
    int sumActiveQty(Long optionId, LocalDateTime now);
}
