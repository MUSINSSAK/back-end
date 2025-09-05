package com.example.musinssak.domain.cart.repository;

import com.example.musinssak.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * [설명]
 * 장바구니 상자를 DB에서 찾아오거나, 저장하는 도구예요.
 * - userId로 내 상자를 찾는 기능이 있어요.
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}
