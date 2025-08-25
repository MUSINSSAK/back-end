package com.example.musinssak.infra.external.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class KakaoAddressSearchDto {

    private List<Document> documents;

    @Getter
    public static class Document {
        @JsonProperty("road_address")
        private RoadAddress roadAddress;

        @JsonProperty("address")
        private JibunAddress jibunAddress;
    }

    @Getter
    public static class RoadAddress {
        @JsonProperty("address_name") private String addressName;
        @JsonProperty("building_name") private String buildingName;
        @JsonProperty("zone_no") private String zoneNo;
    }

    @Getter
    public static class JibunAddress {
        @JsonProperty("address_name") private String addressName;
    }
}
