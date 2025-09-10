package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByOrderNumber(String orderNumber); // 주문번호로 찾음
}
