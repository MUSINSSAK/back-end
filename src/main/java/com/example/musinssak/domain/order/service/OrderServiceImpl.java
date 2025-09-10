package com.example.musinssak.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/** 주문 임시 구현임(다음 단계에서 실제 엔티티 저장함) */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final SecureRandom RND = new SecureRandom();

    @Override
    public CreatedOrder createDraft(Long userId,
                                    List<StockReservationService.ReservationPlan.ReservedItem> items) {

        // 1) 임시 주문번호 생성함
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String orderNo = "ORD" + ts + "-" + (1000 + RND.nextInt(9000));

        // 2) 금액은 다음 단계에서 계산함(지금은 0으로 둠)
        return CreatedOrder.builder()
                .orderNo(orderNo)
                .totalProductAmount(0)
                .discountAmount(0)
                .deliveryFee(0)
                .finalAmount(0)
                .build();
    }
}
