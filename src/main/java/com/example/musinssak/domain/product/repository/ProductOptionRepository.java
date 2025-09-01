package com.example.musinssak.domain.product.repository;

import com.example.musinssak.domain.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    // 상품 id로 옵션(사이즈/재고/가격) 조회
    List<ProductOption> findAllByProductIdOrderByIdAsc(Long productId);
}