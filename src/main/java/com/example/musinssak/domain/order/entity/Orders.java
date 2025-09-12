package com.example.musinssak.domain.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 엔티티임
 * - 실제 DB 테이블 orders 컬럼과 1:1로 맞춤
 * - 컬럼: id, order_number, user_id, orderer_name, orderer_email, orderer_phone,
 *        status, total_product_price, total_discount_price, delivery_fee,
 *        final_payment_price, created_at, expired_at
 */
@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA가 씀
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK임

    @Column(name = "order_number", nullable = false, length = 100, unique = true)
    private String orderNumber; // 주문번호임

    @Column(name = "user_id", nullable = false)
    private Long userId; // 사용자 id임

    // 주문자 정보는 nullable 가능함
    @Column(name = "orderer_name", length = 255)
    private String ordererName; // 주문자 이름임

    @Column(name = "orderer_email", length = 255)
    private String ordererEmail; // 주문자 이메일임

    @Column(name = "orderer_phone", length = 50)
    private String ordererPhone; // 주문자 연락처임

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private OrderStatus status; // 주문 상태임

    @Column(name = "total_product_price", nullable = false)
    private int totalProductPrice; // 상품 원가 합임

    @Column(name = "total_discount_price", nullable = false)
    private int totalDiscountPrice; // 총 할인 금액임

    @Column(name = "delivery_fee", nullable = false)
    private int deliveryFee; // 배송비임

    @Column(name = "final_payment_price", nullable = false)
    private int finalPaymentPrice; // 최종 결제 금액임

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 생성 시각임

    @Column(name = "expired_at")
    private LocalDateTime expiredAt; // 결제 만료 시각임(30분 후 등)

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>(); // 주문 아이템 목록임

    /** 주문아이템 추가함 */
    public void addItem(OrderItem item) {
        this.items.add(item);
        item.setOrder(this);
    }

    @PrePersist
    void onCreate() {
        if (this.createdAt == null) this.createdAt = LocalDateTime.now(); // 생성시간 채움
        // expiredAt은 파사드/서비스에서 30분 뒤로 세팅함
    }
    /** 주문자 정보 세팅함 */
    public void applyOrderer(String name, String email, String phone) {
        this.ordererName = name;
        this.ordererEmail = email;
        this.ordererPhone = phone;
    }

    /** 금액 필드들을 갱신함 */
    public void applyAmounts(int totalProductPrice, int totalDiscountPrice, int deliveryFee, int finalPaymentPrice) {
        this.totalProductPrice = totalProductPrice;
        this.totalDiscountPrice = totalDiscountPrice;
        this.deliveryFee = deliveryFee;
        this.finalPaymentPrice = finalPaymentPrice;
    }

}
