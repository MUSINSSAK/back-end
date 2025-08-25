package com.example.musinssak.api.user.dto;

import com.example.musinssak.domain.user.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {
    private Long id;
    private String label;
    private String recipient;
    private String phone;
    private String address;
    private String detailAddress;
    private String postalCode;
    private boolean isDefault;

    @JsonProperty("isDefault") // 응답 JSON 키를 isDefault로 고정
    public boolean isDefault() {
        return isDefault;
    }

    public static AddressResponse from(Address a) {
        return AddressResponse.builder()
                .id(a.getId())
                .label(a.getLabel())
                .recipient(a.getRecipient())
                .phone(a.getPhone())
                .address(a.getAddress())
                .detailAddress(a.getDetailAddress())
                .postalCode(a.getPostalCode())
                .isDefault(a.isDefault())
                .build();
    }
}
