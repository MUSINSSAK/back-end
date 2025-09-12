// src/main/java/com/example/musinssak/domain/order/entity/OrderDelivery.java
package com.example.musinssak.domain.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA가 씀
@AllArgsConstructor
@Builder
@ToString(exclude = "order") // 로그 찍을 때 순환참조 막음
@Entity
@Table(name = "order_deliveries")
public class OrderDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK임

    /** 주문과 1:1임 */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Orders order; // 주문임

    // ====== DB 컬럼과 1:1 매핑됨 ======
    @Column(name = "recipient", nullable = false, length = 255)
    private String recipient; // 수령인임

    @Column(name = "phone", nullable = false, length = 50)
    private String phone; // 연락처임

    @Column(name = "address", nullable = false, length = 255)
    private String address; // 기본주소임

    @Column(name = "detail_address", length = 255)
    private String detailAddress; // 상세주소임

    @Column(name = "postal_code", length = 20)
    private String postalCode; // 우편번호임

    @Column(name = "delivery_request", length = 255)
    private String deliveryRequest; // 배송요청임

    @Column(name = "estimated_delivery_date")
    private LocalDate estimatedDeliveryDate; // 도착예정일임(옵션)

    // ====== 연관관계 편의 메서드 ======
    /** 주문과 묶음 */
    public void bindOrder(Orders order) { // setOrder 대신 이거 씀
        this.order = order;
    }

    // ====== 변경 메서드 ======
    /**
     * label은 DB에 없음 → 첫 파라미터는 무시함
     */
    public void apply(
            String labelIgnored,
            String recipient,
            String phone,
            String address,
            String detailAddress,
            String postalCode,
            String deliveryRequest
    ) {
        this.recipient = recipient;
        this.phone = phone;
        this.address = address;
        this.detailAddress = detailAddress;
        this.postalCode = postalCode;
        this.deliveryRequest = deliveryRequest;
    }

    /** label 없이 쓰는 버전도 제공함 */
    public void apply(
            String recipient,
            String phone,
            String address,
            String detailAddress,
            String postalCode,
            String deliveryRequest
    ) {
        this.recipient = recipient;
        this.phone = phone;
        this.address = address;
        this.detailAddress = detailAddress;
        this.postalCode = postalCode;
        this.deliveryRequest = deliveryRequest;
    }
}
