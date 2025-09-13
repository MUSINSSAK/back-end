package com.example.musinssak.api.product.facade;

import com.example.musinssak.api.product.dto.ProductQuestionResponse;
import com.example.musinssak.api.product.dto.QuestionSort;

public interface ProductQuestionQueryFacade {
    ProductQuestionResponse getProductQuestions(Long productId, QuestionSort sort, int page, int size);
}