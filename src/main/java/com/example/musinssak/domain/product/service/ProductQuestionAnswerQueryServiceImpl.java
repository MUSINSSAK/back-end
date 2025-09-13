package com.example.musinssak.domain.product.service;

import com.example.musinssak.domain.product.entity.ProductQuestionAnswer;
import com.example.musinssak.domain.product.repository.ProductQuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQuestionAnswerQueryServiceImpl implements ProductQuestionAnswerQueryService {

    private final ProductQuestionAnswerRepository answerRepository;

    @Override
    public Map<Long, ProductQuestionAnswer> findByQuestionIds(Collection<Long> questionIds) {
        if (questionIds == null || questionIds.isEmpty()) return Map.of();

        return answerRepository.findByQuestionIdIn(questionIds).stream()
                .collect(Collectors.toMap(
                        a -> a.getQuestion().getId(),
                        Function.identity()
                ));
    }
}