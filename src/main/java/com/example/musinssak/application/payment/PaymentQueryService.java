// src/main/java/com/example/musinssak/application/payment/PaymentQueryService.java
package com.example.musinssak.application.payment;

import com.example.musinssak.api.payment.dto.PaymentInfoResponse;

/**
 * 결제 조회 서비스 인터페이스임
 * 결제 관련 조회 로직을 정의함
 */
public interface PaymentQueryService {

    /**
     * 주문 ID로 결제 정보를 조회함
     * - 주문 상품 목록과 금액 정보를 가져옴
     * - 배송 정보와 시간 알림을 계산함
     * - 만료된 주문의 경우 예외를 발생시킨다
     *
     * @param orderId 주문 ID (주문번호 문자열)
     * @param userId 사용자 ID (권한 검증용)
     * @return PaymentInfoResponse 결제 정보 응답 DTO
     * @throws BusinessException 주문 미존재, 권한 없음, 시간 만료 등
     */
    PaymentInfoResponse getPaymentInfo(String orderId, Long userId);
}