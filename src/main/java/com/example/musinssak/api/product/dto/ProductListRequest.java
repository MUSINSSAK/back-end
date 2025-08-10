package com.example.musinssak.api.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductListRequest {
    private String category;        // 필수 (예: "beauty" 슬러그)
    private List<String> brand;     // &brand=나이키&brand=반스
    private Integer minPrice;
    private Integer maxPrice;
    private String sort = "recommend"; // 기본값
    private Long cursor;            // 마지막 상품 id
    private Integer size = 12;      // 기본 12
}
