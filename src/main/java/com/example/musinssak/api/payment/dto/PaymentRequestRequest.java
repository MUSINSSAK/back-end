// src/main/java/com/example/musinssak/api/payment/dto/PaymentRequestRequest.java
package com.example.musinssak.api.payment.dto;

import lombok.*;

/**
 * 결제 요청 요청 DTO임
 * POST /api/payments/{orderId}/request 의 요청 구조를 정의함
 */
@Getter
@Builder
@NoArgsConstructor
public class PaymentRequestRequest {

    // 현재는 별도 파라미터가 없음
    // 추후 결제 수단, 할부 개월 등 추가 가능함
}