// src/main/java/com/example/musinssak/domain/order/repository/OrdersRepository.java
package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.Orders;
import com.example.musinssak.domain.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List; // [추가] List를 import 합니다.
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersRepository extends JpaRepository<Orders, Long>, OrdersRepositoryCustom {

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

    /** PAYMENT_PENDING 상태이면서 만료된 주문을 PAYMENT_EXPIRED 로 일괄 변경(스케줄러용) */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        update Orders o
           set o.status = com.example.musinssak.domain.order.entity.OrderStatus.PAYMENT_EXPIRED
         where o.status = com.example.musinssak.domain.order.entity.OrderStatus.PAYMENT_PENDING
           and o.expiredAt <= :now
    """)
    int markPaymentPendingExpired(LocalDateTime now);

    /** 주문 상태를 변경함 (결제 진행 중 등) */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        update Orders o
           set o.status = :newStatus
         where o.id = :orderId
    """)
    int updateOrderStatus(Long orderId, OrderStatus newStatus);

    /** 특정 사용자의 특정 상태 주문 목록 조회 (리뷰 작성 가능 상품 조회용) */
    List<Orders> findByUserIdAndStatus(Long userId, OrderStatus status);
}