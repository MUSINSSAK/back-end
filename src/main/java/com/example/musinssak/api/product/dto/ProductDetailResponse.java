package com.example.musinssak.api.product.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductDetailResponse {
    private Long productId;
    private String brandName;
    private String productName;

    private List<String> images;        // 상세 이미지 URL

    private Integer originalPrice;      // 정가
    private Integer discountedPrice;    // 최종 할인가
    private Integer discountRate;       // 할인율(%)

    private Double rating;              // 평균 별점
    private Integer reviewCount;        // 리뷰 개수

    private List<SizeStock> sizes;

    private String description;
    private List<String> features;

    private Materials materials;
    private List<String> care;

    @Getter @Builder
    public static class SizeStock {
        private String size;   // 문자열로 변경 (FREE, M, L, 230, 235, 컬러명 등)
        private Integer stock;
        // 필요 시 price 필드 추가 가능
    }

    @Getter @Builder
    public static class Materials {
        private List<String> upper;
        private List<String> lining;
        private List<String> outsole;
    }
}
