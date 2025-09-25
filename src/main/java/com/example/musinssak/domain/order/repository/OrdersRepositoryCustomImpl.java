package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.Orders;
import com.example.musinssak.domain.order.entity.OrderStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.musinssak.domain.order.entity.QOrders.orders;

@RequiredArgsConstructor
public class OrdersRepositoryCustomImpl implements OrdersRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Orders> findMyOrderHistory(Long userId, LocalDateTime startDate, List<OrderStatus> statuses, Pageable pageable) {
        List<Orders> content = queryFactory
                .selectFrom(orders)
                .where(
                        orders.userId.eq(userId),
                        createdAtGoe(startDate),
                        statusIn(statuses)
                )
                .orderBy(orders.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(orders.count())
                .from(orders)
                .where(
                        orders.userId.eq(userId),
                        createdAtGoe(startDate),
                        statusIn(statuses)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }

    private BooleanExpression createdAtGoe(LocalDateTime startDate) {
        return startDate != null ? orders.createdAt.goe(startDate) : null;
    }

    private BooleanExpression statusIn(List<OrderStatus> statuses) {
        return statuses != null && !statuses.isEmpty() ? orders.status.in(statuses) : null;
    }
}