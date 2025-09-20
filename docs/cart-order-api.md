# Cart & Order API Guide

## 공통 규칙
- Base URL: `/api`
- 인증: 보호된 엔드포인트는 `Authorization: Bearer <accessToken>` 헤더가 필수입니다.
- 응답 포맷: 모든 성공/실패 응답은 `com.example.musinssak.common.web.ApiResponse` 래퍼를 사용합니다.

```json
// 성공 응답 예시
{
  "status": 200,
  "code": "SUCCESS",
  "data": { ... },
  "message": "선택적 메시지"
}

// 오류 응답 예시
{
  "status": 401,
  "code": "AUTH_REQUIRED",
  "data": null,
  "message": "로그인이 필요합니다."
}
```

오류 시 `code` 값은 `ErrorCode` 열거형을 사용하며, `message`는 이미 한국어로 제공됩니다.

---

## 장바구니(Cart) API
Base Path: `/api/cart`

| 메서드 | 경로 | 설명 | 인증 |
| ------ | ---- | ---- | ---- |
| GET    | `/api/cart` | 현재 사용자 장바구니 조회 | 필요 |
| POST   | `/api/cart` | 상품 옵션을 장바구니에 추가 | 필요 |
| PUT    | `/api/cart/{cartItemId}/quantity` | 장바구니 수량 변경 | 필요 |
| DELETE | `/api/cart/items/{cartItemId}` | 단일 항목 삭제 | 필요 |
| DELETE | `/api/cart/items` | 여러 항목 일괄 삭제 | 필요 |
| PUT    | `/api/cart/select` | 선택/해제 상태 변경(부분/전체) | 필요 |

### 1. GET `/api/cart`
- 설명: 사용자 장바구니 전체를 반환합니다. 장바구니가 비어 있어도 200 응답과 빈 리스트를 보냅니다.
- 응답: `CartGetResponse`

```json
{
  "status": 200,
  "code": "SUCCESS",
  "data": {
    "userId": 7,
    "cartItems": [
      {
        "cartItemId": 11,
        "productId": 101,
        "productName": "베이직 티셔츠",
        "brandName": "MUSINSSAK",
        "productImageUrl": "...",
        "size": "M",
        "quantity": 2,
        "originalPrice": 29000,
        "salePrice": 19000,
        "discountRate": 35,
        "selected": true,
        "stock": 15
      }
    ],
    "selectedCount": 1,
    "totalProductAmount": 38000,
    "totalCount": 1,
    "totalPrice": 58000,
    "discountAmount": 20000,
    "deliveryFee": 0,
    "finalAmount": 38000
  }
}
```
- 오류: 미인증 시 401 `AUTH_REQUIRED`.

### 2. POST `/api/cart`
- 설명: 상품 옵션을 장바구니에 담습니다. 이미 존재하면 수량을 증가시킵니다.
- 요청 본문:

```json
{
  "productOptionId": 123,
  "quantity": 2
}
```

- 성공: 200, 메시지 "장바구니에 담았습니다." (`data`는 `null`).
- 대표 오류
  - 400 `CART_ITEM_QUANTITY_INVALID`: 수량이 1 미만.
  - 404 `PRODUCT_OPTION_NOT_FOUND`: 존재하지 않는 옵션.
  - 409 `OUT_OF_STOCK`: 재고 부족.

### 3. PUT `/api/cart/{cartItemId}/quantity`
- 설명: 특정 장바구니 항목의 수량을 갱신합니다.
- 요청 본문:

```json
{
  "quantity": 3
}
```

- 응답: `CartChangeQuantityResponse`

```json
{
  "status": 200,
  "code": "SUCCESS",
  "data": {
    "cartItemId": 11,
    "oldQuantity": 2,
    "newQuantity": 3,
    "itemTotalPrice": 57000,
    "availableStock": 10,
    "canIncrease": true,
    "canDecrease": true
  }
}
```

- 오류
  - 404 `CART_ITEM_NOT_FOUND`: 항목 없음 또는 타 사용자 항목.
  - 409 `OUT_OF_STOCK`: 재고 부족.

### 4. DELETE `/api/cart/items/{cartItemId}`
- 설명: 단일 장바구니 항목을 삭제합니다.
- 응답: `CartDeleteResponse` (`deletedCount`, `remainingItems`).
- 오류: 404 `CART_ITEM_NOT_FOUND`.

### 5. DELETE `/api/cart/items`
- 설명: 여러 항목을 한 번에 삭제합니다.
- 요청 본문:

```json
{
  "cartItemIds": [11, 12, 13]
}
```

- 응답: `CartDeleteResponse`.
- 오류
  - 400 `INVALID_REQUEST`: ID 배열이 비어 있음.
  - 404 `CART_ITEM_NOT_FOUND`: 존재하지 않는 항목.

### 6. PUT `/api/cart/select`
- 설명: 장바구니 선택 상태를 변경합니다. `selectAll` 또는 `cartItemIds` 중 하나를 사용합니다.
- 요청 본문 예시
  - 일부 선택: `{"cartItemIds": [11, 12], "isSelected": true}`
  - 전체 해제: `{"selectAll": true, "isSelected": false}`
- 응답: `CartSelectResponse` (`selectedCount`, `selectedTotalPrice`).
- 오류: 400 `INVALID_REQUEST` (필수 필드 누락 또는 잘못된 조합).

---

## 주문(Order) API
Base Path: `/api/orders`

| 메서드 | 경로 | 설명 | 인증 |
| ------ | ---- | ---- | ---- |
| POST   | `/api/orders/create` | 장바구니 선택 항목을 주문으로 생성 | 필요 |
| GET    | `/api/orders/{orderId}/items` | 주문 상품 목록 및 금액 조회 | 필요 |
| POST   | `/api/orders/{orderId}/prepare` | 주문자/배송지 정보 저장 + 금액 재계산 | 필요 |
| PUT    | `/api/orders/{orderId}` | `prepare`와 동일 기능, PUT 형태 | 필요 |

### 1. POST `/api/orders/create`
- 설명: 선택한 장바구니 항목으로 주문을 생성하고 30분 유효 재고 예약을 걸어둡니다.
- 요청 본문:

```json
{
  "cartItemIds": [11, 12]
}
```

- 응답: `OrderCreateResponse`

```json
{
  "status": 200,
  "code": "SUCCESS",
  "data": {
    "orderPk": 55,
    "orderId": "ORD202509171030-1a2b",
    "orderItems": [
      {
        "productId": 101,
        "productName": "베이직 티셔츠",
        "brandName": "MUSINSSAK",
        "size": "M",
        "quantity": 2,
        "originalPrice": 29000,
        "salePrice": 19000
      }
    ],
    "totalProductAmount": 58000,
    "discountAmount": 20000,
    "deliveryFee": 0,
    "finalAmount": 38000,
    "reservationExpiresAt": "2025-09-17T11:00:00"
  }
}
```

- 대표 오류
  - 400 `NO_SELECTED_ITEMS`: 선택된 장바구니 항목이 없음.
  - 404 `PRODUCT_OPTION_NOT_FOUND`: 옵션 누락.
  - 409 `OUT_OF_STOCK`: 재고 부족.

### 2. GET `/api/orders/{orderId}/items`
- 설명: 주문 요약과 주문 상품 리스트를 반환합니다.
- 응답: `OrderItemsResponse`
- 만료 처리: 주문 상태가 `CREATED`이고 만료 시간이 지났다면 즉시 400 `ORDER_TIME_EXPIRED`를 반환합니다.
- 권한 오류: 403 `FORBIDDEN` (로그인은 되었지만 다른 사용자의 주문 접근 시).

### 3. POST `/api/orders/{orderId}/prepare`
- 설명: 주문 페이지에서 입력한 주문자/배송지 정보를 저장하고, 최신 가격 정보를 재계산합니다.
- 요청 본문: `OrderUpdateRequest`

```json
{
  "deliveryInfo": {
    "recipient": "홍길동",
    "phone": "010-1234-5678",
    "address": "서울특별시 강남구 ...",
    "detailAddress": "101동 101호",
    "postalCode": "01234",
    "deliveryRequest": "문 앞에 놔주세요"
  },
  "ordererInfo": {
    "name": "홍길동",
    "email": "hong@example.com",
    "phone": "010-1234-5678"
  }
}
```

- 응답: `OrderUpdateResponse` (`priceBreakdown`, `remainingTime`, `deliveryInfo` 등 포함).
- 오류
  - 400 `ORDER_TIME_EXPIRED`: 만료된 주문.
  - 403 `AUTH_FORBIDDEN`: 다른 사용자 주문.
  - 404 `ORDER_NOT_FOUND`: 주문 없음.
  - 404 `PRODUCT_OPTION_NOT_FOUND`: 금액 재계산 중 옵션 누락.

### 4. PUT `/api/orders/{orderId}`
- 설명: `prepare`와 동일한 로직을 PUT 메서드로 제공한 버전입니다.
- 요청/응답/오류는 3번과 동일합니다.

---

## 프론트 구현 체크리스트
1. 모든 보호 엔드포인트 호출 전에 JWT 액세스 토큰을 확보하고 `Authorization` 헤더에 추가합니다.
2. 오류 응답의 `message`는 이미 한국어이므로 사용자에게 바로 노출할 수 있습니다.
3. 장바구니 수량/선택 UI는 서버 응답의 `canIncrease`, `canDecrease`, `selectedCount`, `selectedTotalPrice` 등을 이용해 즉시 반영합니다.
4. 주문 프로세스는 만료 가능성이 있으므로 400 `ORDER_TIME_EXPIRED` 수신 시 재주문 안내를 표시합니다.
5. 주문 금액 표시는 항상 서버가 재계산한 값을 우선 사용하고, 프론트 계산은 보조 설명용으로만 사용합니다.
