
-- 도커에만 실행된다
-- 사용자 생성 및 권한 설정
-- CREATE USER IF NOT EXISTS 'MUSINSSAK'@'%' IDENTIFIED BY '1234';
-- GRANT ALL PRIVILEGES ON musinsa_shop.* TO 'MUSINSSAK'@'%';
-- FLUSH PRIVILEGES;

-- (중요)모든 외래키를 마지막에 한 번에 추가
-- Step 1: 최상위 부모 테이블 생성
CREATE TABLE brands (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(100),
    phone VARCHAR(50),
    birth_date DATE,
    profile_image_url VARCHAR(255),
    last_password_modified_at DATETIME,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE coupons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT, -- 쿠폰 설명 추가
    type VARCHAR(50), -- PERCENT, AMOUNT, FREE_DELIVERY
    discount_rate INT, -- % 할인
    discount_amount INT, -- 정액 할인
    min_order_price INT, -- 최소 주문 금액
    max_discount_amount INT, -- 최대 할인 한도
    expired_at DATETIME -- 만료일
);

CREATE TABLE inquiry_types (
    code VARCHAR(50) PRIMARY KEY,
    label VARCHAR(100)
);


-- Step 2: 사용자 기반 서브 테이블 생성
CREATE TABLE password_reset_tokens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    email VARCHAR(255),
    code VARCHAR(255),
    expires_at DATETIME,
    is_verified BOOLEAN,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE addresses ( -- 회원이 저장해둔 주소록
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    label VARCHAR(255),
    recipient VARCHAR(255),
    phone VARCHAR(50),
    address VARCHAR(255),
    detail_address VARCHAR(255),
    postal_code VARCHAR(20),
    is_default BOOLEAN,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE uploaded_files (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    file_name VARCHAR(255),
    file_url VARCHAR(255),
    created_at DATETIME
);

CREATE TABLE points (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    total_amount INT,
    updated_at DATETIME
    -- "적립률(earn rate)은 테이블이 아닌 코드 내 상수로 관리함 (예: 0.5 → 50%)"
);

CREATE TABLE carts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE inquiries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    category VARCHAR(50),
    title VARCHAR(255),
    content TEXT,
    status VARCHAR(50),
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    product_id BIGINT,
    rating INT,
    content TEXT,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE product_questions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    product_id BIGINT,
    type VARCHAR(50),
    title VARCHAR(255),
    content TEXT,
    status VARCHAR(50),
    created_at DATETIME
);

CREATE TABLE product_question_answers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_id BIGINT,
    responder_type VARCHAR(50),
    content TEXT,
    answered_at DATETIME
);

CREATE TABLE user_coupons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    coupon_id BIGINT,
    is_expiring_soon BOOLEAN,
    created_at DATETIME
);

-- Step 3: 상품 관련 테이블 생성
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 상품 ID
    brand_id BIGINT,                      -- 브랜드 ID (외래키)
    category_id BIGINT,                   -- 카테고리 ID (외래키)
    name VARCHAR(255),                    -- 상품명
    thumbnail_image_url VARCHAR(255),     -- 대표 썸네일 이미지 URL
    original_price INT,                   -- 정가
    discount_rate INT,                    -- 할인율 (%)
    discounted_price INT,                 -- 할인가
    description TEXT,                     -- 상품 설명
    features JSON,                        -- 상품 특징 목록
    materials JSON,                       -- 소재 정보 (upper, lining, outsole 등)
    care JSON,                            -- 세탁 및 관리법
    created_at DATETIME,                  -- 생성일
    updated_at DATETIME                   -- 수정일
);

CREATE TABLE product_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    image_url VARCHAR(255),
    sort_order INT -- 이미지 순서 지정 (선택)
);

CREATE TABLE product_options (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 옵션 ID (기본 키)
    product_id BIGINT,                    -- 연결된 상품 ID
    size VARCHAR(50),                     -- 사이즈 (예: 250, M, L 등)
    stock INT,                            -- 현재 재고 수량
    price INT                             -- 해당 옵션의 가격
);

CREATE TABLE shipping_return_policies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    shipping_fee INT,
    free_shipping_threshold INT,
    shipping_period VARCHAR(100),
    shipping_regions VARCHAR(255),
    return_period VARCHAR(100),
    cost_policy VARCHAR(255),
    non_returnable_reasons TEXT
);

-- Step 4: 사용자 행동 관련 테이블 생성
    CREATE TABLE wishlists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    product_id BIGINT,
    created_at DATETIME
);

CREATE TABLE recent_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    product_id BIGINT,
    viewed_at DATETIME
    -- "최대 100개까지 저장 (서비스로직)"
);

CREATE TABLE cart_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id BIGINT,
    product_option_id BIGINT,             -- 상품+사이즈 조합
    quantity INT,                         -- 담은 수량
    is_selected BOOLEAN,                  -- 선택 여부 (주문에 포함할지)
    created_at DATETIME
);

CREATE TABLE review_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    review_id BIGINT,
    image_url VARCHAR(255)
);

CREATE TABLE inquiry_answers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    inquiry_id BIGINT,
    content TEXT,
    answered_at DATETIME
);

-- Step 5: 주문 관련 테이블 생성
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 주문 ID (기본 키)
    order_number VARCHAR(100) UNIQUE,     -- 외부 노출용 주문 번호 (예: ORD20250801001)
    user_id BIGINT,                       -- 주문한 사용자 ID
    -- (불변 기록용)
    orderer_name VARCHAR(255),            -- 주문 당시 주문자 이름
    orderer_email VARCHAR(255),           -- 주문 당시 주문자 이메일
    orderer_phone VARCHAR(50),            -- 주문 당시 주문자 전화번호

    status VARCHAR(50),                   -- 주문 상태 (예: CREATED, PAID, CANCELLED)
    total_product_price INT,              -- 총 상품 금액
    total_discount_price INT,             -- 총 할인 금액
    delivery_fee INT,                     -- 배송비
    final_payment_price INT,              -- 최종 결제 금액
    created_at DATETIME,                  -- 주문 생성 시각
    expired_at DATETIME                   -- 주문 만료 시각
);

CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    product_id BIGINT,
    product_option_id BIGINT,
    quantity INT,
    price INT,
    discount_price INT
);

CREATE TABLE order_deliveries ( -- 해당 주문의 실제 배송지 정보
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    recipient VARCHAR(255),
    phone VARCHAR(50),
    address VARCHAR(255),
    detail_address VARCHAR(255),
    postal_code VARCHAR(20),
    delivery_request VARCHAR(255),
    estimated_delivery_date DATE
);

CREATE TABLE stock_reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,                      -- 주문 ID (주문과 연결)
    user_id BIGINT,                       -- 예약한 사용자 ID (추적 및 최적화용)
    product_option_id BIGINT,             -- 상품 옵션 ID
    quantity INT,                         -- 예약 수량
    reservation_expires_at DATETIME       -- 예약 만료 시간
);

CREATE TABLE order_discounts ( -- 할인/적립 관련 데이터를 추적하는 역할
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,                      -- 주문 ID (주문 1건에 대한 할인/적립 내역)
    coupon_id BIGINT,                     -- 사용한 쿠폰 ID (없으면 null 가능)
    coupon_discount_amount INT,           -- 쿠폰으로 할인된 금액 (예: 5000원 할인)
    points_used INT,                      -- 사용한 포인트 금액 (예: -3000원 차감)
    points_earned INT                     -- 해당 주문으로 적립된 포인트 (예: 500원 적립)

    -- 예) 주문 A: 쿠폰 5,000원 할인 + 포인트 3,000원 사용 + 500원 적립
    -- → order_id=101, coupon_discount_amount=5000, points_used=3000, points_earned=500
);

-- Step 6: 결제 관련 테이블 생성
CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    payment_method VARCHAR(50),
    amount INT,
    status VARCHAR(50),
    paid_at DATETIME,
    pg_transaction_id VARCHAR(255)
);

CREATE TABLE payment_cards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_id BIGINT,
    card_number VARCHAR(100),
    expiry_date VARCHAR(20),
    cvc VARCHAR(10),
    issuer VARCHAR(50),
    auth_code VARCHAR(50)
);

CREATE TABLE payment_kakaopay (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_id BIGINT,
    tid VARCHAR(100),
    redirect_url VARCHAR(255),
    mobile_url VARCHAR(255)
    -- expires_at PG 정책상 만료 시간 관리 (추가 고려)
);

CREATE TABLE payment_iamport (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_id BIGINT,
    imp_uid VARCHAR(100),
    merchant_uid VARCHAR(100),
    buyer_name VARCHAR(100),
    buyer_email VARCHAR(255),
    buyer_tel VARCHAR(50)
);

-- Step 7: 적립금 내역 생성
CREATE TABLE point_histories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    point_id BIGINT,
    type VARCHAR(50),
    amount INT,
    description TEXT,
    created_at DATETIME,
    expired_at DATETIME
    -- "적립금은 정책에 따라 자동 적립되며, 적립률은 서비스 코드에서 계산"
);


-- Step 2 외래키
-- 1. password_reset_tokens → users
ALTER TABLE password_reset_tokens
    ADD CONSTRAINT fk_password_reset_tokens_user -- 외래키 제약조건의 "이름"
        FOREIGN KEY (user_id) REFERENCES users(id);

-- 2. addresses → users
ALTER TABLE addresses
    ADD CONSTRAINT fk_addresses_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- 3. uploaded_files → users
ALTER TABLE uploaded_files
    ADD CONSTRAINT fk_uploaded_files_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- 4. points → users
ALTER TABLE points
    ADD CONSTRAINT fk_points_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- 5. carts → users
ALTER TABLE carts
    ADD CONSTRAINT fk_carts_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- 6. inquiries → users
ALTER TABLE inquiries
    ADD CONSTRAINT fk_inquiries_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- 7. reviews → users
ALTER TABLE reviews
    ADD CONSTRAINT fk_reviews_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- 8. product_questions → users
ALTER TABLE product_questions
    ADD CONSTRAINT fk_product_questions_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- 9. product_question_answers → product_questions
ALTER TABLE product_question_answers
    ADD CONSTRAINT fk_product_question_answers_question
        FOREIGN KEY (question_id) REFERENCES product_questions(id);

-- 10. user_coupons → users
ALTER TABLE user_coupons
    ADD CONSTRAINT fk_user_coupons_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- 10-2. user_coupons → coupons
ALTER TABLE user_coupons
    ADD CONSTRAINT fk_user_coupons_coupon
        FOREIGN KEY (coupon_id) REFERENCES coupons(id);

-- product_questions.type → inquiry_types.code
ALTER TABLE product_questions
    ADD CONSTRAINT fk_product_questions_type
        FOREIGN KEY (type) REFERENCES inquiry_types(code);

-- Step 3 외래키
-- products → brands
ALTER TABLE products
    ADD CONSTRAINT fk_products_brand
        FOREIGN KEY (brand_id) REFERENCES brands(id);

-- products → categories
ALTER TABLE products
    ADD CONSTRAINT fk_products_category
        FOREIGN KEY (category_id) REFERENCES categories(id);

-- product_images → products
ALTER TABLE product_images
    ADD CONSTRAINT fk_product_images_product
        FOREIGN KEY (product_id) REFERENCES products(id);

-- product_options → products
ALTER TABLE product_options
    ADD CONSTRAINT fk_product_options_product
        FOREIGN KEY (product_id) REFERENCES products(id);

-- shipping_return_policies → products
ALTER TABLE shipping_return_policies
    ADD CONSTRAINT fk_shipping_return_policies_product
        FOREIGN KEY (product_id) REFERENCES products(id);

-- reviews → products
ALTER TABLE reviews
    ADD CONSTRAINT fk_reviews_product
        FOREIGN KEY (product_id) REFERENCES products(id);

-- product_questions → products
ALTER TABLE product_questions
    ADD CONSTRAINT fk_product_questions_product
        FOREIGN KEY (product_id) REFERENCES products(id);

-- Step 4 외래키
-- wishlists → users
ALTER TABLE wishlists
    ADD CONSTRAINT fk_wishlists_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- wishlists → products
ALTER TABLE wishlists
    ADD CONSTRAINT fk_wishlists_product
        FOREIGN KEY (product_id) REFERENCES products(id);

-- recent_products → users
ALTER TABLE recent_products
    ADD CONSTRAINT fk_recent_products_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- recent_products → products
ALTER TABLE recent_products
    ADD CONSTRAINT fk_recent_products_product
        FOREIGN KEY (product_id) REFERENCES products(id);

-- cart_items → carts
ALTER TABLE cart_items
    ADD CONSTRAINT fk_cart_items_cart
        FOREIGN KEY (cart_id) REFERENCES carts(id);

-- cart_items → product_options
ALTER TABLE cart_items
    ADD CONSTRAINT fk_cart_items_product_option
        FOREIGN KEY (product_option_id) REFERENCES product_options(id);

-- review_images → reviews
ALTER TABLE review_images
    ADD CONSTRAINT fk_review_images_review
        FOREIGN KEY (review_id) REFERENCES reviews(id);

-- inquiry_answers → inquiries
ALTER TABLE inquiry_answers
    ADD CONSTRAINT fk_inquiry_answers_inquiry
        FOREIGN KEY (inquiry_id) REFERENCES inquiries(id);

-- Step 5 외래키
-- orders → users
ALTER TABLE orders
    ADD CONSTRAINT fk_orders_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- order_items → orders
ALTER TABLE order_items
    ADD CONSTRAINT fk_order_items_order
        FOREIGN KEY (order_id) REFERENCES orders(id);

-- order_items → products
ALTER TABLE order_items
    ADD CONSTRAINT fk_order_items_product
        FOREIGN KEY (product_id) REFERENCES products(id);

-- order_items → product_options
ALTER TABLE order_items
    ADD CONSTRAINT fk_order_items_product_option
        FOREIGN KEY (product_option_id) REFERENCES product_options(id);

-- order_deliveries → orders
ALTER TABLE order_deliveries
    ADD CONSTRAINT fk_order_deliveries_order
        FOREIGN KEY (order_id) REFERENCES orders(id);

-- stock_reservations → orders
ALTER TABLE stock_reservations
    ADD CONSTRAINT fk_stock_reservations_order
        FOREIGN KEY (order_id) REFERENCES orders(id);

-- stock_reservations → users
ALTER TABLE stock_reservations
    ADD CONSTRAINT fk_stock_reservations_user
        FOREIGN KEY (user_id) REFERENCES users(id);

-- stock_reservations → product_options
ALTER TABLE stock_reservations
    ADD CONSTRAINT fk_stock_reservations_product_option
        FOREIGN KEY (product_option_id) REFERENCES product_options(id);

-- order_discounts → orders
ALTER TABLE order_discounts
    ADD CONSTRAINT fk_order_discounts_order
        FOREIGN KEY (order_id) REFERENCES orders(id);

-- order_discounts → coupons
ALTER TABLE order_discounts
    ADD CONSTRAINT fk_order_discounts_coupon
        FOREIGN KEY (coupon_id) REFERENCES coupons(id);

-- Step 6 외래키
-- payments → orders
ALTER TABLE payments
    ADD CONSTRAINT fk_payments_order
        FOREIGN KEY (order_id) REFERENCES orders(id);

-- payment_cards → payments
ALTER TABLE payment_cards
    ADD CONSTRAINT fk_payment_cards_payment
        FOREIGN KEY (payment_id) REFERENCES payments(id);

-- payment_kakaopay → payments
ALTER TABLE payment_kakaopay
    ADD CONSTRAINT fk_payment_kakaopay_payment
        FOREIGN KEY (payment_id) REFERENCES payments(id);

-- payment_iamport → payments
ALTER TABLE payment_iamport
    ADD CONSTRAINT fk_payment_iamport_payment
        FOREIGN KEY (payment_id) REFERENCES payments(id);

-- Step 7 외래키
-- point_histories → points
ALTER TABLE point_histories
    ADD CONSTRAINT fk_point_histories_point
        FOREIGN KEY (point_id) REFERENCES points(id);
