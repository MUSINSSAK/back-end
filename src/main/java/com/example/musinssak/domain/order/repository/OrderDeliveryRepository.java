// src/main/java/com/example/musinssak/domain/order/repository/OrderDeliveryRepository.java
package com.example.musinssak.domain.order.repository;

import com.example.musinssak.domain.order.entity.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Long> {

    /** 주문ID로 배송지 찾음 */
    Optional<OrderDelivery> findByOrder_Id(Long orderId);
}
