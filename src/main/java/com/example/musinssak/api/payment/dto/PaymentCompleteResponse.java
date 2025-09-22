// src/main/java/com/example/musinssak/api/payment/dto/PaymentCompleteResponse.java
package com.example.musinssak.api.payment.dto;

import lombok.*;

/**
 * 결제 완료 응답 DTO임
 * 결제 완료 처리 결과를 프론트엔드에 전송
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCompleteResponse {

    /**
     * 주문번호
     */
    private String orderNumber;

    /**
     * 결제 ID
     */
    private String paymentId;

    /**
     * 포트원 거래 ID
     */
    private String transactionId;

    /**
     * 결제 상태
     */
    private String paymentStatus;

    /**
     * 결제 완료 시간
     */
    private String completedAt;

    /**
     * 최종 결제 금액
     */
    private Long finalAmount;
}