// src/main/java/com/example/musinssak/api/order/dto/OrderUpdateRequest.java
package com.example.musinssak.api.order.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderUpdateRequest {

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DeliveryInfo {
        private String label;                 // DB엔 없음(무시)

        @NotBlank(message = "수령인을 입력해주세요.")
        private String recipient;

        @NotBlank(message = "연락처를 입력해주세요.")
        private String phone;

        @NotBlank(message = "주소를 입력해주세요.")
        private String address;

        private String detailAddress;

        @Size(max = 20)
        private String postalCode;

        private String deliveryRequest;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class OrdererInfo {
        @NotBlank(message = "주문자 이름을 입력해주세요.")
        private String name;

        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "주문자 연락처를 입력해주세요.")
        private String phone;
    }

    private DeliveryInfo deliveryInfo; // nullable
    private OrdererInfo  ordererInfo;  // nullable

    // 향후 확장용(지금은 미사용)
    private Long   couponId;
    private Integer pointsToUse;
}