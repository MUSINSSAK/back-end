package com.example.musinssak.api.user.dto;

import com.example.musinssak.domain.user.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefaultAddressUpdateResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Item {
        private Long id;
        private boolean isDefault;

        @JsonProperty("isDefault") // 응답 JSON 키를 isDefault로 고정
        public boolean isDefault() {
            return isDefault;
        }
    }

    private List<Item> updatedList;

    public static DefaultAddressUpdateResponse from(List<Address> addresses) {
        List<Item> items = addresses.stream()
                .map(a -> Item.builder()
                        .id(a.getId())
                        .isDefault(a.isDefault())
                        .build())
                .toList();
        return DefaultAddressUpdateResponse.builder()
                .updatedList(items)
                .build();
    }
}
