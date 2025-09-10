package com.example.musinssak.application.order.result;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 파사드 출력 모델임
 * - 컨트롤러 응답 DTO와 분리함
 */
@Getter
@Builder
public class CreateOrderResult {

    private final String orderNo;                   // 주문번호임
    private final LocalDateTime reservationExpires; // 만료 시각임

    // 금액 정보임(임시 0, 다음 단계에서 계산/매핑함)
    private final Integer totalProductAmount;
    private final Integer discountAmount;
    private final Integer deliveryFee;
    private final Integer finalAmount;
}
