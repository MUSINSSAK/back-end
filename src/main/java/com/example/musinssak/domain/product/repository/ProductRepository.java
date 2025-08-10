package com.example.musinssak.domain.product.repository;

import com.example.musinssak.api.product.dto.ProductSearchRequest;
import com.example.musinssak.api.product.dto.ProductSearchResultResponse;
import com.example.musinssak.domain.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductRepository = 멀티탭 리모컨
 * 버튼 A: JPA 기본 기능(CRUD, 페이징 등)
 * 버튼 B: 네가 만든 QueryDSL 기능(검색/필터/정렬/커서)
 */
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    List<Product> findTop10ByOrderByCreatedAtDesc();
}