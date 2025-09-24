package com.example.musinssak.domain.product.service;

import com.example.musinssak.domain.product.repository.ProductRepository;
import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductReaderServiceImpl implements ProductReaderService {

    private final ProductRepository productRepository;

    @Override
    public void getByIdOrThrow(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @Override
    public boolean exists(Long productId) {
        return productRepository.existsById(productId);
    }
}