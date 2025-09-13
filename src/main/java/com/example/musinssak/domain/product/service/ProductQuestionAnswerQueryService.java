package com.example.musinssak.domain.product.service;

import com.example.musinssak.domain.product.entity.ProductQuestionAnswer;

import java.util.Collection;
import java.util.Map;

public interface ProductQuestionAnswerQueryService {
    Map<Long, ProductQuestionAnswer> findByQuestionIds(Collection<Long> questionIds);
}