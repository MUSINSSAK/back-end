package com.example.musinssak.domain.review.service;

import com.example.musinssak.domain.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Map;

public interface ReviewQueryService {
    Double getAverageRating(Long productId);  // null이면 리뷰 없음
    long getReviewCount(Long productId);

    long getTotalCount(Long productId);

    Double getAverageRatingRaw(Long productId);

    Map<Integer, Integer> getRatingDistribution(Long productId);

    Page<ReviewRepository.ReviewListRow> getReviewPage(Long productId, int zeroBasedPage, int size, Sort sort);
}