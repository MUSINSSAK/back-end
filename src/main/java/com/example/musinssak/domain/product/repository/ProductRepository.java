package com.example.musinssak.domain.product.repository;

import com.example.musinssak.domain.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("SELECT p FROM Product p JOIN FETCH p.brand ORDER BY p.createdAt DESC")
//    List<Product> findTop6ByOrderByCreatedAtDesc(Pageable pageable);
    List<Product> findTop6ByOrderByCreatedAtDesc();
}
