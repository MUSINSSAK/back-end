package com.example.musinssak.domain.order.service;

import com.example.musinssak.api.review.dto.WritableReviewDto;
import java.util.List;

// [수정] 인터페이스 이름 변경
public interface WritableReviewQueryService {
    List<WritableReviewDto> findWritableReviews(Long userId);
}