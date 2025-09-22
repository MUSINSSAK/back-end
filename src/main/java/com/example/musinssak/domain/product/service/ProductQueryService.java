package com.example.musinssak.domain.product.service;

import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.product.entity.ProductImage;
import com.example.musinssak.domain.product.entity.ProductOption;
import com.example.musinssak.domain.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductQueryService {

    // 상품 + 브랜드를 한 번에 로딩 (없으면 예외)
    Product getProductWithBrandOrThrow(Long productId);

    // 상세 이미지 목록 (sort_order ASC)
    List<ProductImage> getImages(Long productId);

    // 옵션(사이즈/재고/가격) 목록 (id ASC)
    List<ProductOption> getOptions(Long productId);

    void assertExists(Long productId);
}
