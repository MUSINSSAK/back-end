package com.example.musinssak.domain.order.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 주문의 개별 상품 스냅샷 엔티티임
 * - DB 테이블(order_items) 컬럼과 1:1로 맞춤
 * - 컬럼: id, order_id, product_id, product_option_id, quantity, price, discount_price
 */
@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA가 씀
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본키임

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @Setter
    private Orders order; // 소속 주문임

    @Column(name = "product_id", nullable = false)
    private Long productId; // 상품 id임

    @Column(name = "product_option_id", nullable = false)
    private Long productOptionId; // 옵션 id임

    @Column(name = "quantity", nullable = false)
    private int quantity; // 수량임

    @Column(name = "price", nullable = false)
    private int price; // 원가 단가임

    @Column(name = "discount_price", nullable = false)
    private int discountPrice; // 할인 단가임
}
