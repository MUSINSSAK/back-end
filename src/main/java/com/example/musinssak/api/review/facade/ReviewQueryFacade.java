package com.example.musinssak.api.review.facade;

import com.example.musinssak.api.review.dto.ReviewListResponse;
import com.example.musinssak.api.review.dto.SortType;
import jakarta.validation.constraints.Min;

public interface ReviewQueryFacade {
    ReviewListResponse getProductReviews(Long productId, SortType sortType, int zeroBasedPage, @Min(1) int size);
}
