package com.example.musinssak.domain.review.service;

public interface ReviewQueryService {
    Double getAverageRating(Long productId);  // null이면 리뷰 없음
    long getReviewCount(Long productId);
}