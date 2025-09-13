package com.example.musinssak.domain.product.service;

public interface ProductReaderService {
    void getByIdOrThrow(Long productId);
    boolean exists(Long productId); // 상품 존재 여부만 boolean 으로 확인할 수 있는 exists(productId) 메서드 구현
}