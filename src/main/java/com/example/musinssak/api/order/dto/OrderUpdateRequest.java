// src/main/java/com/example/musinssak/api/order/dto/OrderUpdateRequest.java
package com.example.musinssak.api.order.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class OrderUpdateRequest {

    /** 배송지 정보임(선택적으로 전부/일부 보낼 수 있음) */
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DeliveryInfo {
        private String label;            // 예: 집/회사 등임
        @NotBlank private String recipient;      // 수령인임
        @NotBlank private String phone;          // 연락처임(형식검증은 이후단계)
        @NotBlank private String address;        // 기본주소임
        private String detailAddress;    // 상세주소임
        private String postalCode;       // 우편번호임
        private String deliveryRequest;  // 배송요청사항임(선택)
    }

    /** 주문자 정보임(프로필에서 가져온 값을 기본으로 보냄) */
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class OrdererInfo {
        @NotBlank private String name;
        @Email @NotBlank private String email;
        @NotBlank private String phone;
    }

    private DeliveryInfo deliveryInfo;   // 배송지 블록임
    private OrdererInfo ordererInfo;     // 주문자 블록임

    // 자리만 잡아둠(후속 단계에서 사용함)
    private Long couponId;               // 사용 쿠폰 id임(optional)
    private Integer pointsToUse;         // 사용할 적립금임(optional)
}
