package com.example.musinssak.api.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAddressRequest {

    @NotBlank private String label;
    @NotBlank private String recipient;
    @NotBlank private String phone;
    @NotBlank private String postalCode;
    @NotBlank private String address;
    private String detailAddress;

    // 입력(JSON) → 필드 매핑 고정
    @JsonProperty("isDefault")
    private boolean isDefault;
}
