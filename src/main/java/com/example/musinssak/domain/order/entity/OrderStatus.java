package com.example.musinssak.domain.order.entity;

/** 주문 상태임 */
public enum OrderStatus {
    CREATED,           // 주문 생성됨(결제 대기임)
    PAYMENT_PENDING,   // 결제 진행 중임
    PAID,              // 결제 완료됨
    PAYMENT_FAILED,    // 결제 실패됨
    PAYMENT_EXPIRED,   // 결제 기한 만료됨
    CANCELLED,         // 취소됨
    EXPIRED            //
}
