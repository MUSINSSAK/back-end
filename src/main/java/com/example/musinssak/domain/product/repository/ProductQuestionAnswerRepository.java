package com.example.musinssak.domain.product.repository;

import com.example.musinssak.domain.product.entity.ProductQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductQuestionAnswerRepository extends JpaRepository<ProductQuestionAnswer, Long> {

    // 질문 1개 : 답변 1개 구조 → 단건 조회
    Optional<ProductQuestionAnswer> findByQuestionId(Long questionId);

    // 페이지 조회 시 N+1 방지를 위한 일괄 조회
    List<ProductQuestionAnswer> findByQuestionIdIn(Collection<Long> questionIds);
}