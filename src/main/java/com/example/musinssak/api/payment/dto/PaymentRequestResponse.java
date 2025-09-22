// src/main/java/com/example/musinssak/api/payment/dto/PaymentRequestResponse.java
package com.example.musinssak.api.payment.dto;

import lombok.*;

/**
 * 결제 요청 응답 DTO임
 * POST /api/payments/{orderId}/request 의 응답 구조를 정의함
 * 프론트엔드에서 포트원 결제창을 띄우기 위한 정보를 포함함
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestResponse {

    private String paymentId;          // 포트원 결제 ID임 (결제 식별자)
    private String merchantId;         // 가맹점 ID임 (storeId)
    private String channelKey;         // V2 API 채널 키임
    private String orderName;          // 주문명임 (예: "에어맥스 270 외 2건")
    private int totalAmount;           // 총 결제 금액임
    private String currency;           // 통화 코드임 (KRW)
    private String customerName;       // 고객 이름임
    private String customerEmail;      // 고객 이메일임
    private String customerPhone;      // 고객 전화번호임
    private String returnUrl;          // 결제 완료 후 리다이렉트 URL임
    private String notificationUrl;    // 웹훅 수신 URL임 (결제 검증용)

    /**
     * 주문명을 생성함
     * - 단일 상품: "상품명"
     * - 다중 상품: "대표상품명 외 N건"
     */
    public static String createOrderName(String firstProductName, int totalProductCount) {
        if (totalProductCount == 1) {
            return firstProductName;
        } else {
            return firstProductName + " 외 " + (totalProductCount - 1) + "건";
        }
    }
}