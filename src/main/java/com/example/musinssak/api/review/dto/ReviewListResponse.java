package com.example.musinssak.api.review.dto;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record ReviewListResponse(
        // 상품 식별자 (DB PK) → 보통 null은 없지만,
        // DTO 조립 중 세팅 안 됐을 수도 있어 null 허용(Long) 사용
        Long productId,

        // 총 리뷰 수 → "없음"은 없고 최소 0은 항상 존재하므로 primitive long
        long totalReviews,

        // 평균 별점 → 항상 값이 있음 (없으면 0.0으로 치환하므로 primitive double)
        double averageRating,

        // 별점 분포 (5~1) → 맵이 반드시 존재 (없으면 빈 맵 보정)
        Map<String, Integer> ratingDistribution,

        // 리뷰 항목 리스트 (없으면 빈 리스트로 내려감)
        List<ReviewItem> reviews,

        // 페이지 번호 → 요청에서 필수, null 불가이므로 primitive int (1-based 응답)
        int page,

        // 페이지 크기 → 요청에서 필수, null 불가이므로 primitive int
        int size,

        // 다음 페이지 여부 → 항상 true/false가 존재하므로 primitive boolean
        boolean hasNext
) {}