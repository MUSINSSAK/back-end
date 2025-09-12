// src/main/java/com/example/musinssak/api/order/dto/OrderUpdateResponse.java
package com.example.musinssak.api.order.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class OrderUpdateResponse {

    private String orderId;                 // 주문번호임
    private String status;                  // 주문상태 문자열임
    private String reservationExpiresAt;    // 만료시각 ISO임
    private String remainingTime;           // 남은 시간 mm:ss 형식임

    private PriceBreakdown priceBreakdown;  // 금액 요약임
    private OrderUpdateRequest.DeliveryInfo deliveryInfo; // 저장된 배송지 echo임

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PriceBreakdown {
        private int originalProductAmount; // 총 원가 합임
        private int productDiscount;       // 총 할인 합임(상품 자체 할인)
        private int productAmount;         // 할인 적용된 상품 금액 합임
        private int couponDiscount;        // 쿠폰 할인 금액임(현재 0으로 둠)
        private int pointsUsed;            // 사용 적립금임(현재 0으로 둠)
        private int totalDiscount;         // 전체 할인 합(상품+쿠폰+적립금)임
        private int deliveryFee;           // 배송비임
        private int finalAmount;           // 최종 결제 금액임
    }
}
