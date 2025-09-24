package com.example.musinssak.api.review.facade;

import com.example.musinssak.api.review.dto.MyReviewListResponse;
import com.example.musinssak.api.review.dto.ReviewCreateRequest;
import com.example.musinssak.api.review.dto.ReviewUpdateRequest;
// [수정] import 경로에 .entity 추가
import com.example.musinssak.domain.user.entity.User;

public interface MyReviewFacade {
    MyReviewListResponse getMyReviews(User user);
    void createReview(User user, ReviewCreateRequest request);
    void updateReview(User user, Long reviewId, ReviewUpdateRequest request);
    void deleteReview(User user, Long reviewId);
}