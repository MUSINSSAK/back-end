// src/main/java/com/example/musinssak/domain/order/repository/OrdersRepository.java
package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    /** 주문번호로 단건 찾음 */
    Optional<Orders> findByOrderNumber(String orderNumber);

    /** 내 주문 단건 찾음(소유자 검증용) */
    Optional<Orders> findByIdAndUserId(Long id, Long userId);

    /** 주문번호로 내 주문 찾음(소유자 검증용) */
    Optional<Orders> findByOrderNumberAndUserId(String orderNumber, Long userId);

    /** CREATED 상태이면서 만료된 주문을 PAYMENT_EXPIRED 로 일괄 변경(스케줄러용) */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        update Orders o
           set o.status = com.example.musinssak.domain.order.entity.OrderStatus.PAYMENT_EXPIRED
         where o.status = com.example.musinssak.domain.order.entity.OrderStatus.CREATED
           and o.expiredAt <= :now
    """)
    int markExpired(LocalDateTime now);

    /** 단건 즉시 만료 처리(조회/갱신 시 발견되면 바로 반영) */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        update Orders o
           set o.status = com.example.musinssak.domain.order.entity.OrderStatus.PAYMENT_EXPIRED
         where o.id = :orderId
           and o.status = com.example.musinssak.domain.order.entity.OrderStatus.CREATED
    """)
    int markPaymentExpiredNow(Long orderId);
}
