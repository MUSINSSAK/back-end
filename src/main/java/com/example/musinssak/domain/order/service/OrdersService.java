package com.example.musinssak.domain.order.service;

import com.example.musinssak.domain.order.entity.OrderItem;
import com.example.musinssak.domain.order.entity.OrderStatus;
import com.example.musinssak.domain.order.entity.Orders;

import java.time.LocalDateTime;
import java.util.List;

/** 주문 도메인 서비스임(비즈니스 규칙만 함) */
public interface OrdersService {
    Orders createOrder(Long userId,
                       OrderStatus status,
                       int totalProductPrice,
                       int totalDiscountPrice,
                       int deliveryFee,
                       int finalPaymentPrice,
                       LocalDateTime expiredAt,
                       List<OrderItem> items);
}
