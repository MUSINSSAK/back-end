package com.example.musinssak.api.product;

import com.example.musinssak.api.product.dto.GetProductQuestionsRequest;
import com.example.musinssak.api.product.dto.ProductQuestionResponse;
import com.example.musinssak.api.product.dto.QuestionSort;
import com.example.musinssak.api.product.facade.ProductQuestionQueryFacade;
import com.example.musinssak.common.web.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductQuestionQueryController {

    private final ProductQuestionQueryFacade productQuestionQueryFacade;

    /**
     * 상품 상세 페이지 → 문의 탭 클릭 시 호출
     * 비로그인 사용자도 조회 가능
     * 정렬: latest(기본), pendingFirst
     * 페이지: 1-based
     */
    @GetMapping("/api/products/{productId}/questions")
    public ApiResponse<ProductQuestionResponse> getProductQuestions(
            @PathVariable("productId") Long productId,
            @Valid @ModelAttribute GetProductQuestionsRequest request
    ) {
        // sort 파싱 (null -> LATEST)
        QuestionSort sort = request.resolveSort();

        ProductQuestionResponse data = productQuestionQueryFacade.getProductQuestions(
                productId,
                sort,
                request.getPage(),
                request.getSize()
        );

        return ApiResponse.success("상품 문의 목록을 성공적으로 조회했습니다.", data);
    }
}