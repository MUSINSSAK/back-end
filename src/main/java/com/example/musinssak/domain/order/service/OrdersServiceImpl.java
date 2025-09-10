package com.example.musinssak.domain.order.service;

import com.example.musinssak.domain.order.entity.OrderItem;
import com.example.musinssak.domain.order.entity.OrderStatus;
import com.example.musinssak.domain.order.entity.Orders;
import com.example.musinssak.domain.order.repository.OrderItemRepository;
import com.example.musinssak.domain.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/** 주문 저장을 담당함 */
@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Orders createOrder(Long userId,
                              OrderStatus status,
                              int totalProductPrice,
                              int totalDiscountPrice,
                              int deliveryFee,
                              int finalPaymentPrice,
                              LocalDateTime expiredAt,
                              List<OrderItem> items) {

        // 주문번호 만듦(간단 포맷임)
        String orderNumber = "ORD" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +
                "-" + UUID.randomUUID().toString().substring(0, 4);

        // 주문 엔티티 만듦
        Orders orders = Orders.builder()
                .orderNumber(orderNumber)
                .userId(userId)
                .status(status)
                .totalProductPrice(totalProductPrice)
                .totalDiscountPrice(totalDiscountPrice)
                .deliveryFee(deliveryFee)
                .finalPaymentPrice(finalPaymentPrice)
                .expiredAt(expiredAt)
                .build();

        // 아이템 붙임
        for (OrderItem item : items) {
            orders.addItem(item); // 양방향 세팅됨
        }

        // 저장함(cascade로 아이템도 저장됨)
        return ordersRepository.save(orders);
    }
}
