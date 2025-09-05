package com.example.musinssak.domain.product.service;

import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.product.entity.Product;
import com.example.musinssak.domain.product.entity.ProductImage;
import com.example.musinssak.domain.product.entity.ProductOption;
import com.example.musinssak.domain.product.repository.ProductImageRepository;
import com.example.musinssak.domain.product.repository.ProductOptionRepository;
import com.example.musinssak.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;

    @Override
    public Product getProductWithBrandOrThrow(Long productId) {
        return productRepository.findByIdWithBrand(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @Override
    public List<ProductImage> getImages(Long productId) {
        return productImageRepository.findAllByProductIdOrderBySortOrderAsc(productId);
    }

    @Override
    public List<ProductOption> getOptions(Long productId) {
        return productOptionRepository.findAllByProductIdOrderByIdAsc(productId);
    }

    @Override
    public void assertExists(Long productId) {

        if (productId == null || !productRepository.existsById(productId)) {
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }
}
