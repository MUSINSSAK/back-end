package com.example.musinssak.domain.order.service;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/** 주문을 생성/저장하는 서비스임 */
public interface OrderService {

    /** 예약된 아이템으로 주문서를 임시 생성함(임시 구현임) */
    CreatedOrder createDraft(Long userId,
                             List<StockReservationService.ReservationPlan.ReservedItem> items);

    /** 생성된 주문 요약임 */
    @Getter @Builder
    class CreatedOrder {
        private final String orderNo;        // 주문번호임
        private final int totalProductAmount; // 상품 원가 합임
        private final int discountAmount;     // 총 할인 금액임
        private final int deliveryFee;        // 배송비임
        private final int finalAmount;        // 최종 결제 금액임
    }
}
