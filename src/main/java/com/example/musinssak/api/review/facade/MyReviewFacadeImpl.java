package com.example.musinssak.api.review.facade;

import com.example.musinssak.api.review.dto.*;
// [수정] import 경로 변경
import com.example.musinssak.domain.order.service.WritableReviewQueryService;
import com.example.musinssak.domain.review.entity.Review;
import com.example.musinssak.domain.review.repository.ReviewRepository;
import com.example.musinssak.domain.review.service.ReviewService;
import com.example.musinssak.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MyReviewFacadeImpl implements MyReviewFacade {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    // [수정] 주입받는 서비스의 타입과 변수 이름 변경
    private final WritableReviewQueryService writableReviewQueryService;

    @Override
    public MyReviewListResponse getMyReviews(User user) {
        List<Review> reviews = reviewRepository.findByUser_IdOrderByCreatedAtDesc(user.getId());
        List<WrittenReviewDto> writtenReviews = reviews.stream()
                .map(review -> WrittenReviewDto.builder()
                        .reviewId(review.getId())
                        .productId(review.getProduct().getId())
                        .productName(review.getProduct().getName())
                        .rating(review.getRating().doubleValue())
                        .content(review.getContent())
                        .build())
                .collect(Collectors.toList());

        // [수정] 변경된 서비스의 메소드를 호출
        List<WritableReviewDto> writableReviews = writableReviewQueryService.findWritableReviews(user.getId());

        return MyReviewListResponse.builder()
                .writtenReviews(writtenReviews)
                .writableReviews(writableReviews)
                .build();
    }

    // ... create, update, delete 메소드는 동일 ...
    @Override
    public void createReview(User user, ReviewCreateRequest request) {
        reviewService.createReview(user, request);
    }

    @Override
    public void updateReview(User user, Long reviewId, ReviewUpdateRequest request) {
        reviewService.updateReview(user, reviewId, request);
    }

    @Override
    public void deleteReview(User user, Long reviewId) {
        reviewService.deleteReview(user, reviewId);
    }
}