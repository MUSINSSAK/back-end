// src/main/java/com/example/musinssak/api/payment/dto/PaymentCompleteRequest.java
package com.example.musinssak.api.payment.dto;

import lombok.*;

/**
 * 결제 완료 요청 DTO임
 * 프론트엔드에서 결제 성공 후 백엔드에 전송하는 데이터
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCompleteRequest {

    /**
     * 포트원 거래 ID
     * 예: "01996b15-f75e-b834-dc6c-2d193c4634da"
     */
    private String transactionId;

    /**
     * 결제 상태
     * 예: "PAID", "FAILED", "CANCELLED"
     */
    private String status;

    /**
     * 실제 결제된 금액
     * 주문 금액과 일치하는지 검증용
     */
    private Long paidAmount;
}