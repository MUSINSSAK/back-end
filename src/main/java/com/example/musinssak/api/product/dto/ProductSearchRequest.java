package com.example.musinssak.api.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductSearchRequest { // 프론트 -> 백엔드 (프론트에서 보낸 검색 조건)
    private String keyword;
    private String category;
    private List<String> brand;        // brand=NIKE,ZARA
    private Integer minPrice;
    private Integer maxPrice;
    private String sort = "recommend"; // 기본값
    private Long cursor;               // (레거시) id만 담기는 커서 — new 정렬에서만 사용
//    private String cursorToken;        // 권장: 정렬별 안정 커서
    private Integer size = 12;         // 기본 페이지 크기
}