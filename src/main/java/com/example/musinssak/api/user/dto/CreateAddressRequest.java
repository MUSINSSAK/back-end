package com.example.musinssak.api.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAddressRequest {

    // 값이 꼭 있고, 공백 말고 실제 글자여야 한다
    @NotBlank private String label;       // 집/회사 등
    @NotBlank private String recipient;   // 수령인
    @NotBlank private String phone;       // 연락처
    @NotBlank private String postalCode;  // 우편번호
    @NotBlank private String address;     // 기본 주소
    private String detailAddress;         // 상세 주소(선택)

    @JsonProperty("isDefault")            // 요청 본문 키 고정
    private boolean isDefault;            // 기본 배송지로 설정 여부
}
