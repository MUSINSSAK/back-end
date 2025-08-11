package com.example.musinssak.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductSearchResultResponse { // 상품 리스트 + 무한스크롤 정보 포함
    private List<ProductSearchResponse> products;
    private Long nextCursor; // 다음 페이지용 커서 (없으면 null)
    private boolean hasNext; // 다음 페이지 존재 여부
}

