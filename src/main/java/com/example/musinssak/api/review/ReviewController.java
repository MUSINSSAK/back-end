package com.example.musinssak.api.review;

import com.example.musinssak.api.review.dto.ReviewListResponse;
import com.example.musinssak.api.review.dto.SortType;
import com.example.musinssak.api.review.facade.ReviewQueryFacade;
import com.example.musinssak.common.web.ApiResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/{productId}/reviews")
public class ReviewController {

    private final ReviewQueryFacade reviewQueryFacade;

    /**
     * 상품 리뷰 목록 조회
     * GET /api/products/{productId}/reviews
     *
     * @param productId 조회할 상품 ID
     * @param sort 정렬 기준 (latest, high, low) - 기본값 latest
     * @param page 페이지 번호 (1부터 시작)
     * @param size 페이지당 리뷰 개수
     * @return 상품 리뷰 목록과 요약 통계, 페이지 정보
     */
    @GetMapping
    public ResponseEntity<ApiResponse<ReviewListResponse>> getProductReviews(
            @PathVariable("productId") Long productId,
            @RequestParam(name = "sort", required = false, defaultValue = "latest") String sort,
            @RequestParam(name = "page") @Min(1) int page,
            @RequestParam(name = "size") @Min(1) int size
    ) {
        SortType sortType = SortType.from(sort);
        int zeroBasedPage = page - 1;

        ReviewListResponse data = reviewQueryFacade.getProductReviews(
                productId, sortType, zeroBasedPage, size
        );

        return ResponseEntity.ok(
                ApiResponse.success("상품 리뷰 목록을 성공적으로 조회했습니다.", data)
        );
    }
}