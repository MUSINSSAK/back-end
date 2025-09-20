// src/main/java/com/example/musinssak/api/payment/dto/PaymentInfoResponse.java
package com.example.musinssak.api.payment.dto;

import lombok.*;

import java.util.List;

/**
 * 결제 정보 조회 응답 DTO임
 * GET /api/payments/{orderId}/info 의 응답 구조를 정의함
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfoResponse {

    private Long orderPk;                     // 주문 PK임 (DB의 primary key)
    private String orderId;                   // 주문번호임 (ORD20250726001)
    private List<OrderItem> orderItems;      // 주문 상품 목록임
    private PaymentSummary paymentSummary;   // 결제 금액 요약임
    private DeliveryInfo deliveryInfo;       // 배송 정보임
    private String remainingTime;            // 남은 시간임 (MM:SS 형태)
    private TimeAlert timeAlert;             // 시간 알림 정보임

    /**
     * 주문 상품 정보를 담는 내부 클래스임
     * 각 상품의 상세 정보와 개별 금액 계산 결과를 포함함
     */
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItem {
        private Long productId;                // 상품 ID임
        private String productName;            // 상품명임
        private String brandName;              // 브랜드명임
        private String thumbnailImageUrl;      // 상품 썸네일 이미지 URL임
        private String size;                   // 상품 사이즈임 (옵션)
        private int quantity;                  // 주문 수량임
        private int originalPrice;             // 상품 원가임 (개당)
        private int discountedPrice;           // 상품 할인가임 (개당)
        private boolean hasDiscount;           // 할인 여부임 (원가 != 할인가)
        private int totalOriginalPrice;        // 개별 상품 총 원가임 (원가 × 수량)
        private int totalDiscountedPrice;      // 개별 상품 총 할인가임 (할인가 × 수량)
    }

    /**
     * 결제 금액 요약 정보를 담는 내부 클래스임
     * 전체 주문의 금액 계산 과정을 단계별로 보여줌
     */
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentSummary {
        private int originalProductAmount;     // 총 상품 원가임 (모든 상품 원가 합계)
        private int productDiscount;           // 상품 자체 할인 금액임
        private int productAmount;             // 총 상품 금액임 (할인 적용 후)
        private int couponDiscount;            // 쿠폰 할인 금액임
        private int pointsUsed;                // 적립금 사용 금액임
        private int totalDiscount;             // 총 할인 금액임 (상품할인 + 쿠폰 + 적립금)
        private int deliveryFee;               // 배송비임
        private int finalAmount;               // 최종 결제 금액임
    }

    /**
     * 배송 정보를 담는 내부 클래스임
     * 주문 시 입력된 배송지 정보를 그대로 반환함
     */
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeliveryInfo {
        private String recipient;              // 수령인 이름임
        private String phone;                  // 수령인 연락처임
        private String address;                // 주소임
        private String detailAddress;          // 상세 주소임
        private String postalCode;             // 우편번호임
        private String deliveryRequest;        // 배송 요청사항임
    }

    /**
     * 시간 알림 정보를 담는 내부 클래스임
     * 주문 만료까지 남은 시간에 따라 알림 메시지를 생성함
     */
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TimeAlert {
        private boolean shouldShowAlert;       // 알림 표시 여부임 (10분 미만 시 true)
        private String alertType;              // 알림 타입임 (WARNING, DANGER 등)
        private String alertMessage;           // 알림 메시지임
        private int remainingMinutes;          // 남은 시간(분)임
    }
}