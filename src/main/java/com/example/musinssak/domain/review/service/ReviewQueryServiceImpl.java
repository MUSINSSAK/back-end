package com.example.musinssak.domain.review.service;

import com.example.musinssak.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public Double getAverageRating(Long productId) {
        return reviewRepository.findAverageRatingByProductId(productId);
    }

    @Override
    public long getReviewCount(Long productId) {
        return reviewRepository.countByProduct_Id(productId);
    }

    @Override
    public long getTotalCount(Long productId) {
        // DB에서 해당 상품의 리뷰 총 개수만 카운트 (가볍고 빠름)
        return reviewRepository.countByProduct_Id(productId);
    }

    @Override
    public Double getAverageRatingRaw(Long productId) {
        // 리뷰가 없으면 null이 올 수 있음 → 파사드에서 0.0 처리 & 반올림
        return reviewRepository.findAverageRatingByProductId(productId);
    }

    @Override
    public Map<Integer, Integer> getRatingDistribution(Long productId) {
        List<Object[]> buckets = reviewRepository.countGroupByRating(productId);
        Map<Integer, Integer> result = new HashMap<>();
        for (Object[] row : buckets) {
            Integer rating = (Integer) row[0]; // 별점
            Long cnt = (Long) row[1];          // 개수
            result.put(rating, cnt.intValue());
        }
        return result; // {5=89, 4=25, 3=13 ...}
    }

    @Override
    public Page<ReviewRepository.ReviewListRow> getReviewPage(Long productId, int zeroBasedPage, int size, Sort sort) {
        return reviewRepository.findReviewRowsByProductId(productId, PageRequest.of(zeroBasedPage, size, sort));
    }
}
