package com.example.musinssak.infra.external.kakao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoAddressClient {

    private final RestTemplate restTemplate;
    private final KakaoLocalProperties kakaoLocalProperties;

    /**
     * 카카오 로컬 주소검색 API 호출
     * GET {baseUrl}/v2/local/search/address.json?query={query}
     * @param query 검색어 (예: "서울 강남구")
     * @param size 검색 결과 최대 개수 (기본 10)
     * @return 카카오 주소검색 API의 응답
     */
    public KakaoAddressSearchDto searchAddress(String query, int size) {
        String url = UriComponentsBuilder
                .fromHttpUrl(kakaoLocalProperties.getBaseUrl())
                .path("/v2/local/search/address.json")
                .queryParam("query", query)
                .queryParam("size", size)   // 검색 결과 개수
                .build().toUriString();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoLocalProperties.getRestApiKey());
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            // GET 요청 보내기
            ResponseEntity<KakaoAddressSearchDto> response =
                    restTemplate.exchange(url, HttpMethod.GET, entity, KakaoAddressSearchDto.class);

            // 응답 반환
            return response.getBody();
        } catch (Exception e) {
            log.error("주소 검색 API 호출 실패: {}", e.getMessage(), e);
            throw new RuntimeException("주소 검색 서비스 오류");
        }
    }
}
