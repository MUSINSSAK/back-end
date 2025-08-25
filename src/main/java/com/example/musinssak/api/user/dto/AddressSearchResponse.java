package com.example.musinssak.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AddressSearchResponse {

    private List<Item> addresses;

    @Getter
    @AllArgsConstructor
    public static class Item {
        private String roadAddress;   // 도로명 주소
        private String jibunAddress;  // 지번 주소
        private String zipCode;       // 우편번호(5자리)
        private String buildingName;  // 건물명
    }
}
