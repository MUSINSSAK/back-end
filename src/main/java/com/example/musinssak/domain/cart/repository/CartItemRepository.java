
package com.example.musinssak.domain.cart.repository;

import com.example.musinssak.domain.cart.entity.CartItem;
import com.example.musinssak.domain.cart.repository.view.CartItemRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndProductOptionId(Long cartId, Long productOptionId);

    // 장바구니 화면용 조인 조회 함
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
}
