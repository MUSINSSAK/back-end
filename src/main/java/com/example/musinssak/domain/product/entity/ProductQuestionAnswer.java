// src/main/java/com/example/musinssak/domain/product/entity/ProductQuestionAnswer.java
package com.example.musinssak.domain.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_question_answers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductQuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 질문 1개 : 답변 1개 (DB에서 question_id UNIQUE + NOT NULL과 일치)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false, unique = true)
    private ProductQuestion question;

    // SELLER, ADMIN 등
    @Column(name = "responder_type", length = 50)
    private String responderType;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "answered_at")
    private LocalDateTime answeredAt;
}