package com.example.musinssak.domain.review.service;

import com.example.musinssak.api.review.dto.ReviewCreateRequest;
import com.example.musinssak.api.review.dto.ReviewUpdateRequest;
// [수정] import 경로에 .entity 추가
import com.example.musinssak.domain.user.entity.User;

public interface ReviewService {
    void createReview(User user, ReviewCreateRequest request);
    void updateReview(User user, Long reviewId, ReviewUpdateRequest request);
    void deleteReview(User user, Long reviewId);
}