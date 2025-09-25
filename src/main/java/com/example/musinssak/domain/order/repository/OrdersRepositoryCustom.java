package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.Orders;
// [추가] OrderStatus를 import 해주세요!
import com.example.musinssak.domain.order.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepositoryCustom {
    Page<Orders> findMyOrderHistory(Long userId, LocalDateTime startDate, List<OrderStatus> statuses, Pageable pageable);
}