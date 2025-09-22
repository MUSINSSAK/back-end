// src/main/java/com/example/musinssak/domain/order/schedule/OrderExpireScheduler.java
package com.example.musinssak.domain.order.schedule;

import com.example.musinssak.domain.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderExpireScheduler {

    private final OrdersRepository ordersRepository;

    /** 1분마다 주문 만료 처리함 */
    @Scheduled(fixedDelay = 60_000) // 1분 간격으로 돈됨
    @Transactional
    public void expire() {
        LocalDateTime now = LocalDateTime.now();
        log.info("=== 스케줄러 만료 처리 시작: {} ===", now);

        // 1) CREATED 상태 만료 처리
        log.info("CREATED 상태 만료 처리 시작");
        int createdExpired = ordersRepository.markExpired(now);
        log.info("CREATED 상태 만료 처리 결과: {}건", createdExpired);

        // 2) PAYMENT_PENDING 상태 만료 처리
        log.info("PAYMENT_PENDING 상태 만료 처리 시작");
        int paymentPendingExpired = ordersRepository.markPaymentPendingExpired(now);
        log.info("PAYMENT_PENDING 상태 만료 처리 결과: {}건", paymentPendingExpired);

        // 3) 전체 만료 처리 요약
        int totalExpired = createdExpired + paymentPendingExpired;
        log.info("=== 스케줄러 만료 처리 완료: 총 {}건 (CREATED: {}, PAYMENT_PENDING: {}) ===",
                totalExpired, createdExpired, paymentPendingExpired);
    }
}
