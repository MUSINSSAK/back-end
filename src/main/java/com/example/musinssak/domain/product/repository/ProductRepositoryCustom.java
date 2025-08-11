package com.example.musinssak.domain.product.repository;

import com.example.musinssak.api.product.dto.ProductListRequest;
import com.example.musinssak.api.product.dto.ProductSearchRequest;
import com.example.musinssak.api.product.dto.ProductSearchResultResponse;

public interface ProductRepositoryCustom {
    ProductSearchResultResponse searchProducts(ProductSearchRequest request);
    ProductSearchResultResponse listProducts(ProductListRequest request);
}