// src/main/java/com/example/musinssak/domain/order/repository/view/OrderItemRow.java
package com.example.musinssak.domain.order.repository.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 주문아이템 화면용 뷰 DTO임(JPQL new 로 채움) */
@Getter
@AllArgsConstructor
public class OrderItemRow {
    private Long productId;      // 상품 id임
    private String productName;  // 상품명임
    private String brandName;    // 브랜드명임
    private String size;         // 옵션 사이즈임
    private int quantity;        // 수량임
    private int originalPrice;   // 원가 단가임
    private int salePrice;       // 할인가 단가임
}
