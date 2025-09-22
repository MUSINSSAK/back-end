package com.example.musinssak.domain.product.repository;

import com.example.musinssak.domain.product.entity.ProductOption;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    // 상품 id로 옵션(사이즈/재고/가격) 조회
    List<ProductOption> findAllByProductIdOrderByIdAsc(Long productId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select po from ProductOption po where po.id = :id")
    Optional<ProductOption> findForUpdateById(Long id);

}