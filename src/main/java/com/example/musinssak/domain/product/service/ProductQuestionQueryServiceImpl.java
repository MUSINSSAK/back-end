package com.example.musinssak.domain.product.service;

import com.example.musinssak.domain.product.entity.ProductQuestion;
import com.example.musinssak.domain.product.repository.ProductQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQuestionQueryServiceImpl implements ProductQuestionQueryService {

    private final ProductQuestionRepository productQuestionRepository;

    @Override
    public Page<ProductQuestion> getQuestionsPage(Long productId, QuestionSortOption sort, int page, int size) {
        // API는 1-based, JPA는 0-based → 여기서 변환(추후 공통 유틸로 이동 가능)
        int pageIndex = Math.max(page - 1, 0);
        PageRequest pageable = PageRequest.of(pageIndex, size);

        return switch (sort) {
            case PENDING_FIRST -> productQuestionRepository.findPendingFirstByProductId(productId, pageable);
            case LATEST -> productQuestionRepository.findLatestByProductId(productId, pageable);
        };
    }
}