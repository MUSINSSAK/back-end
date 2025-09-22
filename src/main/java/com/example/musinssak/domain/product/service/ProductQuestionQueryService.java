package com.example.musinssak.domain.product.service;

import com.example.musinssak.domain.product.entity.ProductQuestion;
import org.springframework.data.domain.Page;

public interface ProductQuestionQueryService {
    Page<ProductQuestion> getQuestionsPage(Long productId, QuestionSortOption sort, int page, int size);
}