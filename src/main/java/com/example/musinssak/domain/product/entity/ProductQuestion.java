package com.example.musinssak.domain.product.entity;

import com.example.musinssak.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_questions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성자 (User 연관관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 상품 (Product 연관관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 문의 유형 (예: 배송, 상품상세, 재입고 등)
    @Column(length = 50)
    private String type;

    // 문의 제목
    @Column()
    private String title;

    // 문의 내용
    @Column(columnDefinition = "TEXT")
    private String content;

    // 상태 (예: PENDING, ANSWERED)
    @Column(length = 50)
    private String status;

    // 작성일
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}