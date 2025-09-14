// src/main/java/com/example/musinssak/api/order/dto/OrderPrepareRequest.java
package com.example.musinssak.api.order.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderPrepareRequest {

    /** 배송지 입력값임 */
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DeliveryInfo {
        private String label;           // DB엔 없지만 화면 라벨용임(무시됨)
        private String recipient;
        private String phone;
        private String address;
        private String detailAddress;
        private String postalCode;
        private String deliveryRequest;
    }

    /** 주문자(이름/이메일/연락처) 입력값임 */
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class OrdererInfo {
        private String name;
        private String email;
        private String phone;
    }

    private DeliveryInfo deliveryInfo; // 필수
    private OrdererInfo ordererInfo;   // 필수

    // 아래 둘은 다음 단계에서 쓸 예정(지금은 값만 들고 다님)
    private Long couponId;        // optional
    private Integer pointsToUse;  // optional
}
