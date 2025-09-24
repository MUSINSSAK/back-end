// src/main/java/com/example/musinssak/domain/order/repository/OrderItemRepository.java
package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.OrderItem;
import com.example.musinssak.domain.order.repository.view.OrderItemRow;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

/** 주문아이템 레포지토리임 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /** 주문아이템을 상품/브랜드/옵션까지 조인해서 화면용으로 가져옴(내 주문만) */
    @Query("""
        select new com.example.musinssak.domain.order.repository.view.OrderItemRow(
            oi.product.id,
            p.name,
            b.name,
            po.size,
            oi.quantity,
            oi.price,
            oi.discountPrice,
            p.thumbnailImageUrl
        )
        from OrderItem oi
          join oi.order o
          join ProductOption po on po.id = oi.productOptionId
          join po.product p
          join p.brand b
        where o.id = :orderId
          and o.userId = :userId
        order by oi.id asc
    """)
    List<OrderItemRow> findRowsByOrderIdAndUserId(Long orderId, Long userId);

    /** 주문 id로 아이템들 가져옴 */
    List<OrderItem> findByOrder_Id(Long orderId);
}
