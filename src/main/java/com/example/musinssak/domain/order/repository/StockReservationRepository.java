// src/main/java/com/example/musinssak/domain/order/repository/StockReservationRepository.java
package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.StockReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface StockReservationRepository extends JpaRepository<StockReservation, Long> {

    /** 만료 전(=현재 시각 기준 살아있는) 예약 수량 합을 구함 */
    @Query("""
       select coalesce(sum(sr.quantity), 0)
         from StockReservation sr
        where sr.productOptionId = :productOptionId
          and sr.reservationExpiresAt > :now
    """)
    long sumUnexpiredReservedQty(Long productOptionId, LocalDateTime now); // 0 보장됨

    /** 서비스에서 쓰던 이름 유지용 별칭임 */
    default long sumActiveQty(Long productOptionId, LocalDateTime now) {
        return sumUnexpiredReservedQty(productOptionId, now);
    }
}
