// src/main/java/com/example/musinssak/domain/order/repository/OrdersRepository.java
package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findByOrderNumber(String orderNumber); // 기존 메서드임

    /** CREATED 상태이면서 만료된 주문을 EXPIRED 로 바꿈 */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
       update Orders o
          set o.status = com.example.musinssak.domain.order.entity.OrderStatus.EXPIRED
        where o.status = com.example.musinssak.domain.order.entity.OrderStatus.CREATED
          and o.expiredAt <= :now
    """)
    int markExpired(LocalDateTime now);

    /** 내 주문 단건 찾음(소유자 검증용임) */
    Optional<Orders> findByIdAndUserId(Long id, Long userId);
}
