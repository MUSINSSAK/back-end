package com.example.musinssak.domain.user.service;

import com.example.musinssak.api.user.dto.AddressSearchResponse;
import com.example.musinssak.infra.external.kakao.KakaoAddressClient;
import com.example.musinssak.infra.external.kakao.KakaoAddressSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressSearchServiceImpl implements AddressSearchService {

    private final KakaoAddressClient kakaoAddressClient;

    @Override
    public AddressSearchResponse search(String query) {
        // 1) 카카오 주소검색 API 호출
        KakaoAddressSearchDto searchResult = kakaoAddressClient.searchAddress(query, 10);

        // 2) 응답 -> 우리 DTO로 가공
        List<AddressSearchResponse.Item> items = new ArrayList<>();
        for (KakaoAddressSearchDto.Document document : searchResult.getDocuments()) {
            String roadAddress = document.getRoadAddress() != null ? document.getRoadAddress().getAddressName() : null;
            String jibunAddress = document.getJibunAddress() != null ? document.getJibunAddress().getAddressName() : null;
            String zip = document.getRoadAddress() != null ? document.getRoadAddress().getZoneNo() : null;
            String building = document.getRoadAddress() != null ? document.getRoadAddress().getBuildingName() : null;

            if (roadAddress != null || jibunAddress != null) {
                items.add(new AddressSearchResponse.Item(roadAddress, jibunAddress, zip, building));
            }
        }
        return new AddressSearchResponse(items);
    }
}
