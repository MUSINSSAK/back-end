package com.example.musinssak.domain.review.entity;

// [수정] import 경로에 .entity 추가
import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Review(User user, Product product, Integer rating, String content,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // [추가] 리뷰 내용과 수정 시간을 업데이트하는 메소드
    public void updateContent(String content, LocalDateTime updatedAt) {
        this.content = content;
        this.updatedAt = updatedAt;
    }
}