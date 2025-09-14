// src/main/java/com/example/musinssak/application/order/OrderPrepareService.java
package com.example.musinssak.application.order;

import com.example.musinssak.api.order.dto.OrderUpdateRequest;
import com.example.musinssak.api.order.dto.OrderUpdateResponse;

/**
 * 주문 정보 입력 단계 서비스임.
 * - 주문자/배송지 입력(업서트)
 * - 현재 가격/할인 기준으로 금액 재검증
 * - 결제 전 요약 응답 생성(결제 준비)
 */
public interface OrderPrepareService {

    /**
     * 주문자/배송지 갱신하고 금액 재계산함(= 결제 준비)
     * @param orderId 주문 PK
     * @param userId  요청자(소유자 검증용)
     * @param req     주문자/배송지 등 입력값
     * @return 결제 전 요약/금액 정보
     */
    OrderUpdateResponse updateAndRecalculate(Long orderId, Long userId, OrderUpdateRequest req);
}
