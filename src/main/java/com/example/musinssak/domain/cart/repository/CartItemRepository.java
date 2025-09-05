// src/main/java/com/example/musinssak/domain/cart/repository/CartItemRepository.java
package com.example.musinssak.domain.cart.repository;

import com.example.musinssak.domain.cart.entity.CartItem;
import com.example.musinssak.domain.cart.repository.view.CartItemRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/** 장바구니 아이템 레포지토리임 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    /** 같은 옵션 줄 찾음 */
    Optional<CartItem> findByCartIdAndProductOptionId(Long cartId, Long productOptionId);

    /** 장바구니 화면용 조인 조회함 */
    @Query("""
        select new com.example.musinssak.domain.cart.repository.view.CartItemRow(
            ci.id,
            p.id,
            p.name,
            b.name,
            p.thumbnailImageUrl,
            po.size,
            ci.quantity,
            p.originalPrice,
            p.discountedPrice,
            p.discountRate,
            coalesce(ci.selected, false),
            po.stock
        )
        from CartItem ci
          join ci.cart c
          join ci.productOption po
          join po.product p
          join p.brand b
        where c.id = :cartId
        order by ci.id asc
    """)
    List<CartItemRow> findRowsForCart(Long cartId);

    /** 수량 변경용: 내 줄을 옵션/상품까지 조인해서 가져옴(유저 검증함) */
    @Query("""
      select ci
      from CartItem ci
      join fetch ci.cart c
      join fetch ci.productOption po
      join fetch po.product p
      where ci.id = :cartItemId
        and c.userId = :userId
    """)
    Optional<CartItem> findByIdAndUserIdJoinOption(Long cartItemId, Long userId);

    /** (선택) 카트ID로 검증함 */
    @Query("""
      select ci
      from CartItem ci
      join fetch ci.cart c
      join fetch ci.productOption po
      join fetch po.product p
      where ci.id = :cartItemId
        and c.id = :cartId
    """)
    Optional<CartItem> findByIdAndCartIdJoinOption(Long cartItemId, Long cartId);

    // ====== [추가] 삭제/집계 메서드들임 ======

    /** 단건 삭제: 내 소유 것만 지워짐 (반환: 지워진 행 수) */
    long deleteByIdAndCart_UserId(Long cartItemId, Long userId);

    /** 여러 개 삭제: 내 소유 것만 지워짐 (반환: 지워진 행 수) */
    long deleteByIdInAndCart_UserId(Collection<Long> cartItemIds, Long userId);

    /** 남은 줄 카운트: 내 장바구니의 줄 개수를 셈 */
    long countByCart_UserId(Long userId);
}
