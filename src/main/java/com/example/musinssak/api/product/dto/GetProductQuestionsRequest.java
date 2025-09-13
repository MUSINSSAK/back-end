// src/main/java/com/example/musinssak/api/product/dto/GetProductQuestionsRequest.java
package com.example.musinssak.api.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter                 // ✅ 추가
@NoArgsConstructor       // ✅ 추가 (기본 생성자)
@ToString
public class GetProductQuestionsRequest {

    private String sort; // latest | pendingFirst (nullable 허용 → 기본값 처리)

    @NotNull
    @Min(1)
    private Integer page;

    @NotNull
    @Min(1)
    private Integer size;

    public QuestionSort resolveSort() {
        return QuestionSort.from(sort);
    }
}