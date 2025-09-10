package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.StockReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

/** 재고 예약 레포지토리임 */
public interface StockReservationRepository extends JpaRepository<StockReservation, Long> {

    /** 만료 전 예약 수량 합을 구함 */
    @Query("""
        select coalesce(sum(sr.quantity),0)
        from StockReservation sr
        where sr.productOptionId = :optionId
          and sr.reservationExpiresAt > :now
    """)
    int sumReservedQty(@Param("optionId") Long productOptionId,
                       @Param("now") LocalDateTime now);
}
