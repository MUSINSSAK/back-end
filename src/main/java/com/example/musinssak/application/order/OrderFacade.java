// src/main/java/com/example/musinssak/application/order/OrderFacade.java
package com.example.musinssak.application.order;

import com.example.musinssak.application.order.command.CreateOrderCommand;
import com.example.musinssak.application.order.result.CreateOrderResult;

/** 주문 유스케이스 파사드임 */
public interface OrderFacade {

    /** 장바구니 선택상품으로 주문서를 만듦 */
    CreateOrderResult create(CreateOrderCommand command);
}
