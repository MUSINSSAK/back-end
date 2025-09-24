package com.example.musinssak.domain.review.service;

import com.example.musinssak.api.review.dto.ReviewCreateRequest;
import com.example.musinssak.api.review.dto.ReviewUpdateRequest;
import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.product.repository.ProductRepository;
import com.example.musinssak.domain.review.entity.Review;
import com.example.musinssak.domain.review.repository.ReviewRepository;
// [수정] import 경로에 .entity 추가
import com.example.musinssak.domain.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void createReview(User user, ReviewCreateRequest request) {
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다. ID: " + request.productId()));

        Review review = new Review(
                user,
                product,
                request.rating().intValue(),
                request.content(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void updateReview(User user, Long reviewId, ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다. ID: " + reviewId));

        if (!review.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("리뷰를 수정할 권한이 없습니다.");
        }
        review.updateContent(request.content(), LocalDateTime.now());
    }

    @Override
    @Transactional
    public void deleteReview(User user, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다. ID: " + reviewId));

        if (!review.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("리뷰를 삭제할 권한이 없습니다.");
        }
        reviewRepository.delete(review);
    }
}