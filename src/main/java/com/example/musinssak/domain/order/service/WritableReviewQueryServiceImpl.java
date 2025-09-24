package com.example.musinssak.domain.order.service;

import com.example.musinssak.api.review.dto.WritableReviewDto;
import com.example.musinssak.domain.order.entity.OrderStatus;
import com.example.musinssak.domain.order.repository.OrdersRepository;
import com.example.musinssak.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
// [수정] 클래스 이름 변경 및 WritableReviewQueryService를 구현하도록 변경
public class WritableReviewQueryServiceImpl implements WritableReviewQueryService {

    private final OrdersRepository ordersRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<WritableReviewDto> findWritableReviews(Long userId) {
        List<Long> reviewedProductIds = reviewRepository.findReviewedProductIdsByUserId(userId);

        return ordersRepository.findByUserIdAndStatus(userId, OrderStatus.PAID).stream()
                .flatMap(order -> order.getItems().stream()
                        .map(orderItem -> WritableReviewDto.builder()
                                .productId(orderItem.getProduct().getId())
                                .productName(orderItem.getProduct().getName())
                                .thumbnailImageUrl(orderItem.getProduct().getThumbnailImageUrl())
                                .purchaseDate(order.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                                .build()
                        )
                )
                .filter(dto -> !reviewedProductIds.contains(dto.productId()))
                .distinct()
                .collect(Collectors.toList());
    }
}