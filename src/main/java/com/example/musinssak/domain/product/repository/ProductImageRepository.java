package com.example.musinssak.domain.product.repository;

import com.example.musinssak.domain.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    // 상품 id로 이미지 목록 조회
    List<ProductImage> findAllByProductIdOrderBySortOrderAsc(Long productId);
}