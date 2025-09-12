// src/main/java/com/example/musinssak/application/order/OrderPrepareService.java
package com.example.musinssak.application.order;

import com.example.musinssak.api.order.dto.OrderUpdateRequest;
import com.example.musinssak.api.order.dto.OrderUpdateResponse;

public interface OrderPrepareService {

    /** 주문자/배송지 갱신하고 금액 재계산함 */
    OrderUpdateResponse updateAndRecalculate(Long orderId, Long userId, OrderUpdateRequest req);
}
