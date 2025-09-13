package com.example.musinssak.api.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuestionResponse {
    private Long productId;
    private int totalQuestions;
    private List<QuestionSummary> questions;
    private int page;    // 1-based
    private int size;
    private boolean hasNext;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionSummary {
        private Long id;
        private String author;       // 마스킹된 닉네임 (예: 박**)
        private String status;       // PENDING | ANSWERED
        private String question;     // 문의 내용(content)
        private String questionDate; // yyyy-MM-dd

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private AnswerSummary answer; // 선택적
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerSummary {
        private String responder;   // SELLER | ADMIN 등
        private String content;
        private String answerDate;  // yyyy-MM-dd
    }
}