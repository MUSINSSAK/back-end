package com.example.musinssak.api.product.facade;

import com.example.musinssak.api.product.dto.ProductQuestionMapper;
import com.example.musinssak.api.product.dto.ProductQuestionResponse;
import com.example.musinssak.api.product.dto.QuestionSort;
import com.example.musinssak.domain.product.entity.ProductQuestion;
import com.example.musinssak.domain.product.entity.ProductQuestionAnswer;
import com.example.musinssak.domain.product.service.ProductQuestionAnswerQueryService;
import com.example.musinssak.domain.product.service.ProductQuestionQueryService;
import com.example.musinssak.domain.product.service.ProductReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQuestionQueryFacadeImpl implements ProductQuestionQueryFacade {

    private final ProductReaderService productReaderService;
    private final ProductQuestionQueryService productQuestionQueryService;
    private final ProductQuestionAnswerQueryService answerQueryService;

    @Override
    public ProductQuestionResponse getProductQuestions(Long productId, QuestionSort sort, int page, int size) {
        // 1) 상품 존재 검증 (없으면 BusinessException(PRODUCT_NOT_FOUND) 발생)
        productReaderService.getByIdOrThrow(productId);

        // 2) 문의 페이지 조회 (정렬/페이징은 서비스가 처리)
        Page<ProductQuestion> questionPage =
                productQuestionQueryService.getQuestionsPage(productId, sort.toDomain(), page, size);

        // 3) 답변 일괄 조회 (N+1 방지)
        List<Long> questionIds = questionPage.getContent().stream()
                .map(ProductQuestion::getId)
                .toList();
        Map<Long, ProductQuestionAnswer> answerMap = answerQueryService.findByQuestionIds(questionIds);

        // 4) 엔티티 -> API DTO 매핑 (닉네임 마스킹/날짜 포맷 내부 적용)
        List<ProductQuestionResponse.QuestionSummary> questionDtos = questionPage.getContent().stream()
                .map(q -> ProductQuestionMapper.toQuestionSummary(q, answerMap.get(q.getId())))
                .toList();

        // 5) 응답 조립 (total은 Page의 totalElements 사용 → count 쿼리 이미 실행됨)
        return ProductQuestionResponse.builder()
                .productId(productId)
                .totalQuestions((int) questionPage.getTotalElements())
                .questions(questionDtos)
                .page(page)              // 1-based 그대로
                .size(size)
                .hasNext(questionPage.hasNext())
                .build();
    }
}