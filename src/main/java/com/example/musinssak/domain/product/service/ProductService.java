package com.example.musinssak.domain.product.service;

import com.example.musinssak.api.product.dto.ProductListRequest;
import com.example.musinssak.api.product.dto.ProductMainResponse;
import com.example.musinssak.api.product.dto.ProductSearchRequest;
import com.example.musinssak.api.product.dto.ProductSearchResultResponse;

import java.util.List;


public interface ProductService {
    List<ProductMainResponse> getMainProducts();
    ProductSearchResultResponse searchProducts(ProductSearchRequest request);

    ProductSearchResultResponse listProducts(ProductListRequest request);

//    ProductSearchResultResponse listProducts(ProductListRequest request);
}
