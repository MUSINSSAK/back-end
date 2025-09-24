package com.example.musinssak.domain.order.entity;

// [추가] Product 엔티티를 import 합니다.
import com.example.musinssak.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @Setter
    private Orders order;

    // [수정] productId 필드를 실제 Product 엔티티와의 관계로 변경합니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "product_option_id", nullable = false)
    private Long productOptionId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "discount_price", nullable = false)
    private int discountPrice;
}