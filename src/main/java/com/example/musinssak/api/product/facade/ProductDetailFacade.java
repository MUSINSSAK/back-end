package com.example.musinssak.api.product.facade;

import com.example.musinssak.api.product.dto.ProductDetailResponse;

public interface ProductDetailFacade {
    ProductDetailResponse getProductDetail(Long productId);
}
