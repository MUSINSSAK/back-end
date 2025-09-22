// src/main/java/com/example/musinssak/domain/order/service/StockReservationServiceImpl.java
package com.example.musinssak.domain.order.service;

import com.example.musinssak.common.exception.BusinessException;
import com.example.musinssak.common.exception.ErrorCode;
import com.example.musinssak.domain.order.entity.StockReservation;
import com.example.musinssak.domain.order.repository.StockReservationRepository;
import com.example.musinssak.domain.product.entity.ProductOption;
import com.example.musinssak.domain.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class StockReservationServiceImpl implements StockReservationService {

    private final StockReservationRepository stockReservationRepository; // 예약 저장/조회함
    private final ProductOptionRepository productOptionRepository;       // 옵션/재고 읽음
    private final RedissonClient redissonClient;                         // 레디스 락 씀

    /** 예약을 DB에 저장함 + 락으로 가용재고 체크함 */
    @Override
    @Transactional
    public ReservationPlan reserve(Long orderId, Long userId,
                                   List<ReservationPlan.ReservedItem> items,
                                   LocalDateTime expiresAt) {

        // 0) 같은 옵션 묶어서 수량 합침
        Map<Long, Integer> merged = new HashMap<>();
        for (ReservationPlan.ReservedItem it : items) {
            merged.merge(it.getProductOptionId(), it.getQuantity(), Integer::sum);
        }

        // 1) 데드락 막으려고 옵션 id 오름차순으로 락 잡음
        List<Long> optionIds = new ArrayList<>(merged.keySet());
        optionIds.sort(Comparator.naturalOrder());

        List<RLock> locks = new ArrayList<>();

        try {
            // 2) 옵션별 분산락을 잡음
            for (Long optionId : optionIds) {
                RLock lock = redissonClient.getLock("lock:stock:option:" + optionId); // 락 키 규칙임
                boolean ok = lock.tryLock(5, 30, TimeUnit.SECONDS); // 5초 기다리고 30초 임대함
                if (!ok) {
                    // 락 못 잡으면 재시도 유도용 코드가 더 알맞음
                    throw new BusinessException(ErrorCode.STOCK_RESERVATION_FAILED); // 409 성격임
                }
                locks.add(lock);
            }

            // 3) 가용 재고 계산함 = 실제재고 - (만료 전 예약합)
            LocalDateTime now = LocalDateTime.now();
            for (Long optionId : optionIds) {
                ProductOption po = productOptionRepository.findById(optionId)
                        .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_OPTION_NOT_FOUND)); // 옵션 없음임

                long aliveReserved = stockReservationRepository.sumUnexpiredReservedQty(optionId, now); // 살아있는 예약 합임
                long available = (long) po.getStock() - aliveReserved; // 가용 재고임
                int need = merged.get(optionId);                       // 필요한 수량임

                if (available < need) {
                    throw new BusinessException(ErrorCode.OUT_OF_STOCK); // 재고 부족임
                }
            }

            // 4) 모두 통과하면 예약행 저장함
            List<StockReservation> toSave = new ArrayList<>();
            for (Map.Entry<Long, Integer> e : merged.entrySet()) {
                toSave.add(StockReservation.builder()
                        .orderId(orderId)
                        .userId(userId)
                        .productOptionId(e.getKey())
                        .quantity(e.getValue())
                        .reservationExpiresAt(expiresAt) // 만료 시각 저장됨
                        .build());
            }
            stockReservationRepository.saveAll(toSave); // 일괄 저장함

            // 5) 커밋 후에 락을 풀도록 등록함
            if (TransactionSynchronizationManager.isSynchronizationActive()) {
                TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                    @Override
                    public void afterCompletion(int status) {
                        // 트랜잭션 끝난 뒤에 풀림
                        for (int i = locks.size() - 1; i >= 0; i--) {
                            RLock lock = locks.get(i);
                            if (lock.isHeldByCurrentThread()) {
                                lock.unlock(); // 락 해제함
                            }
                        }
                    }
                });
            } else {
                // 트랜잭션이 없으면 지금 바로 풀음(예외 케이스임)
                for (int i = locks.size() - 1; i >= 0; i--) {
                    RLock lock = locks.get(i);
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }

            // 6) 계획 객체를 만들어 돌려줌
            List<ReservationPlan.ReservedItem> normalizedItems = new ArrayList<>();
            for (Map.Entry<Long, Integer> e : merged.entrySet()) {
                normalizedItems.add(ReservationPlan.ReservedItem.builder()
                        .productOptionId(e.getKey())
                        .quantity(e.getValue())
                        .build());
            }
            return ReservationPlan.builder()
                    .orderId(orderId)
                    .userId(userId)
                    .expiresAt(expiresAt)
                    .items(normalizedItems)
                    .build();

        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt(); // 인터럽트 복구함
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR); // 내부오류로 처리함
        } catch (BusinessException be) {
            throw be; // 비즈니스 예외는 그대로 던짐
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR); // 기타 예외 방어함
        }
        // 주의: 락 해제는 위에서 afterCompletion으로 처리됨
    }
}
