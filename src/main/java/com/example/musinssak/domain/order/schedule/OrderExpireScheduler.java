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
        int changed = ordersRepository.markExpired(LocalDateTime.now()); // 만료시킴
        if (changed > 0) {
            log.info("만료 처리된 주문 개수: {}", changed); // 몇 건 바뀌었는지 로그 찍힘
        }
    }
}
