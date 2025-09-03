
package com.example.musinssak.domain.cart.repository.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 조인 결과 한 줄을 담는 읽기 전용 DTO 임 */
@Getter
@AllArgsConstructor
public class CartItemRow {
    private Long cartItemId;      // 담음
    private Long productId;       // 담음
    private String productName;   // 담음
    private String brandName;     // 담음
    private String imageUrl;      // 담음
    private String size;          // 담음
    private int quantity;         // 담음
    private int originalPrice;    // 담음
    private int salePrice;        // 담음
    private int discountRate;     // 담음
    private boolean selected;     // 담음
    private int stock;            // 담음
}
