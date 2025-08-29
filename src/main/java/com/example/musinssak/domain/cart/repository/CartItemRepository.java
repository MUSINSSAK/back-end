package com.example.musinssak.domain.cart.repository;

import com.example.musinssak.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * [설명]
 * 장바구니 안의 “물건 한 줄(CartItem)”을 DB에서 다루는 도구예
 * - 같은 옵션이 이미 있는지 확인하기 위해
 *   cartId + productOptionId로 찾아보는 기능이 있음
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartIdAndProductOptionId(Long cartId, Long productOptionId);
}
