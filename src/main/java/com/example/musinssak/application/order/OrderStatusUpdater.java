// src/main/java/com/example/musinssak/application/order/OrderStatusUpdater.java
package com.example.musinssak.application.order;

import com.example.musinssak.domain.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderStatusUpdater {

    private final OrdersRepository ordersRepository;

    /** CREATED -> PAYMENT_EXPIRED 한 건 즉시 반영(독립 트랜잭션) */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markPaymentExpiredNow(Long orderId) {
        ordersRepository.markPaymentExpiredNow(orderId);
    }
}
