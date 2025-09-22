// src/main/java/com/example/musinssak/application/payment/PaymentService.java
package com.example.musinssak.application.payment;

import com.example.musinssak.api.payment.dto.PaymentRequestResponse;
import com.example.musinssak.api.payment.dto.PaymentCompleteResponse;

/**
 * 결제 서비스 인터페이스임
 * 결제 요청, 검증, 완료 등의 비즈니스 로직을 정의함
 */
public interface PaymentService {

    /**
     * 결제를 요청함
     * - 주문 상태를 PAYMENT_PENDING으로 변경함
     * - 포트원 결제 ID를 생성함
     * - 프론트엔드에서 포트원 결제창을 띄우기 위한 정보를 반환함
     *
     * @param orderId 주문번호 (문자열)
     * @param userId 사용자 ID (권한 검증용)
     * @return PaymentRequestResponse 결제 요청 정보
     * @throws BusinessException 주문 미존재, 권한 없음, 잘못된 상태 등
     */
    PaymentRequestResponse requestPayment(String orderId, Long userId);

    /**
     * 결제를 검증하고 완료 처리함
     * - 포트원에서 실제 결제 상태를 조회함
     * - 결제 성공 시 주문 상태를 PAID로 변경함
     * - 결제 실패 시 주문 상태를 PAYMENT_FAILED로 변경함
     *
     * @param paymentId 포트원 결제 ID
     * @param userId 사용자 ID (권한 검증용)
     * @param transactionId 포트원 거래 ID
     * @return PaymentCompleteResponse 결제 완료 정보
     * @throws BusinessException 결제 미존재, 권한 없음, 검증 실패 등
     */
    PaymentCompleteResponse completePayment(String paymentId, Long userId, String transactionId);
}