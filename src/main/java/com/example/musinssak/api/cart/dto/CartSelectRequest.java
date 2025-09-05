package com.example.musinssak.api.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CartSelectRequest {
    /** 특정 아이템들만 선택/해제할 때 사용 */
    private List<Long> cartItemIds;

    /** 전체 선택/해제면 true */
    private Boolean selectAll;

    /** true=선택, false=해제 (필수 의미) */
    private Boolean isSelected;
}