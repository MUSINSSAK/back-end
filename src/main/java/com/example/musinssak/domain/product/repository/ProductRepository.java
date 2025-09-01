package com.example.musinssak.domain.product.repository;

import com.example.musinssak.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * ProductRepository = 멀티탭 리모컨
 * 버튼 A: JPA 기본 기능(CRUD, 페이징 등)
 * 버튼 B: 네가 만든 QueryDSL 기능(검색/필터/정렬/커서)
 */
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    List<Product> findTop10ByOrderByCreatedAtDesc();

    // 상품 id로 상품 + 브랜드 조회
    @Query("select p from Product p join fetch p.brand where p.id = :id")
    Optional<Product> findByIdWithBrand(@Param("id") Long id);
}