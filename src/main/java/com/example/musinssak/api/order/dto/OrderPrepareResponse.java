// src/main/java/com/example/musinssak/api/order/dto/OrderPrepareResponse.java
package com.example.musinssak.api.order.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderPrepareResponse {

    private String orderId;         // 주문번호 문자열임(ORDyyyy..)
    private String orderNumber;     // == orderId 동일하게 넣음
    private String orderStatus;     // 예: CREATED / PENDING_PAYMENT
    private Integer finalAmount;    // 최종 결제 예상 금액임
    private String remainingTime;   // 예: "14:23"
    private String expiresAt;       // ISO 문자열

    private PriceBreakdown priceBreakdown; // 금액 쪼개기 요약임
    private DeliveryInfo deliveryInfo;     // 저장된 배송지 echo임
    private OrderSummary orderSummary;     // 주문 아이템 요약임
    private String paymentPageUrl;         // 결제페이지 이동용 샘플 URL

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PriceBreakdown {
        private Integer originalProductAmount; // 상품 원가 총합
        private Integer productDiscount;       // 상품 자체 할인 총합
        private Integer productAmount;         // 할인적용 상품 총액
        private Integer couponDiscount;        // 쿠폰 할인 (지금은 0)
        private Integer pointsUsed;            // 사용 적립금 (지금은 0)
        private Integer totalDiscount;         // 총 할인
        private Integer deliveryFee;           // 배송비
        private Integer finalAmount;           // 결제 예정 금액
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DeliveryInfo {
        private String label;          // 화면용(저장은 안함)
        private String recipient;
        private String phone;
        private String address;
        private String detailAddress;
        private String postalCode;
        private String deliveryRequest;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class OrderSummary {
        private List<Item> items;

        @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
        public static class Item {
            private Long productId;
            private String productName;
            private String brandName;
            private String size;
            private Integer quantity;
            private Integer originalPrice;
            private Integer discountedPrice;
            private Boolean hasDiscount;
            private String thumbnailImageUrl; // 있으면 넣음, 없으면 null
        }
    }
}
