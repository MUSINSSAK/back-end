// src/main/java/com/example/musinssak/api/product/dto/ProductQuestionsMapper.java
package com.example.musinssak.api.product.dto;

import com.example.musinssak.common.util.DateFormatters;
import com.example.musinssak.common.util.NameMasker;
import com.example.musinssak.domain.product.entity.ProductQuestion;
import com.example.musinssak.domain.product.entity.ProductQuestionAnswer;


public final class ProductQuestionMapper {
    private ProductQuestionMapper() {}

    public static ProductQuestionResponse.QuestionSummary toQuestionSummary(
            ProductQuestion q,
            ProductQuestionAnswer answerOrNull
    ) {
        var builder = ProductQuestionResponse.QuestionSummary.builder()
                .id(q.getId())
                .author(NameMasker.mask(q.getUser().getNickname()))
                .status(q.getStatus())
                .question(q.getContent())
                .questionDate(DateFormatters.toYMD(q.getCreatedAt()));

        if (answerOrNull != null) {
            builder.answer(
                    ProductQuestionResponse.AnswerSummary.builder()
                            .responder(answerOrNull.getResponderType())
                            .content(answerOrNull.getContent())
                            .answerDate(DateFormatters.toYMD(answerOrNull.getAnsweredAt()))
                            .build()
            );
        }
        return builder.build();
    }
}