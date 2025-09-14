// src/main/java/com/example/musinssak/domain/order/repository/StockReservationRepository.java
package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.StockReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface StockReservationRepository extends JpaRepository<StockReservation, Long> {

    /** 만료 전(=현재 시각 기준 살아있는) 예약 수량 합을 구함 */
    @Query("""
       select coalesce(sum(sr.quantity), 0)
         from StockReservation sr
        where sr.productOptionId = :productOptionId
          and sr.reservationExpiresAt > :now
    """)
    long sumUnexpiredReservedQty(Long productOptionId, LocalDateTime now);

    /** 서비스에서 쓰던 이름 유지용 별칭임(int 반환으로 맞춰서 캐스팅함) */
    default int sumActiveQty(Long productOptionId, LocalDateTime now) {
        return Math.toIntExact(sumUnexpiredReservedQty(productOptionId, now));
    }

    /** 특정 주문의 ‘살아있는(만료 안 된)’ 예약 행들을 전부 가져옴 */
    @Query("""
       select sr
         from StockReservation sr
        where sr.orderId = :orderId
          and sr.reservationExpiresAt > :now
    """)
    List<StockReservation> findActiveByOrderId(Long orderId, LocalDateTime now);
}
