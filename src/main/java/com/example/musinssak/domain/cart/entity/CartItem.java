package com.example.musinssak.domain.cart.entity;

import com.example.musinssak.domain.product.entity.ProductOption;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(
        name = "cart_items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id","product_option_id"})
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY) @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = LAZY) @JoinColumn(name = "product_option_id", nullable = false)
    private ProductOption productOption;

    @Column(nullable = false)
    private int quantity;

    /** DB 컬럼 is_selected ↔ 자바 필드 selected 매핑 (DB 컬럼은 그대로 사용) */
    @Column(name = "is_selected")   // ← DB는 is_selected
    private Boolean selected;       // ← 자바는 selected (NULL일 수도 있음)

    /** 새 줄 생성 도우미: 규칙 B — 새로 담은 건 기본 미선택(false) */
    public static CartItem newItem(Cart cart, ProductOption option, int qty) {
        CartItem ci = new CartItem();
        ci.cart = cart;
        ci.productOption = option;
        ci.quantity = qty;
        ci.selected = Boolean.FALSE;    // 기본 미선택
        return ci;
    }

    public void changeQuantity(int qty) { this.quantity = qty; }

    /** 혹시 과거 데이터가 NULL이면 미선택(false)로 보정 */
    public void markUnselectedIfNull() {
        if (selected == null) selected = Boolean.FALSE;
    }

    /** 저장/수정 시에도 NULL 방지 보정 */
    @PrePersist @PreUpdate
    void fillDefaults() {
        if (selected == null) selected = Boolean.FALSE;
    }
}
