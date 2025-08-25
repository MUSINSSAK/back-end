-- 목적
--  1) 기본 쿠폰 4종 생성 (중복 방지)
--  2) 특정 유저에게 쿠폰 지급
--  3) 적립금 5,000원 지급 + 이력 기록 (재실행 안전)

-- 1) 세션 변수에 이메일 저장
SET @USER_EMAIL := 'sinbumjun123@naver.com';

-- 2) 유저 아이디를 세션 변수에 담기 (collation 맞춤)
SELECT id INTO @USER_ID
FROM users
WHERE email = CONVERT(@USER_EMAIL USING utf8mb4) COLLATE utf8mb4_unicode_ci
    LIMIT 1;

-- 1) 기본 쿠폰 4종 (존재하지 않을 때만 생성)
INSERT INTO coupons (name, description, type, discount_rate, discount_amount, min_order_price, max_discount_amount, expired_at)
SELECT '10% 할인', '전 상품 10% 할인', 'PERCENT', 10, NULL, 30000, 30000, DATE_ADD(NOW(), INTERVAL 180 DAY) FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM coupons WHERE name='10% 할인');

INSERT INTO coupons (name, description, type, discount_rate, discount_amount, min_order_price, max_discount_amount, expired_at)
SELECT '가입 축하 쿠폰 20%', '신규 가입 축하 20% 할인', 'PERCENT', 20, NULL, 50000, 30000, DATE_ADD(NOW(), INTERVAL 30 DAY) FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM coupons WHERE name='가입 축하 쿠폰 20%');

INSERT INTO coupons (name, description, type, discount_rate, discount_amount, min_order_price, max_discount_amount, expired_at)
SELECT '10000원 할인', '정액 1만원 할인', 'AMOUNT', NULL, 10000, 70000, NULL, DATE_ADD(NOW(), INTERVAL 60 DAY) FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM coupons WHERE name='10000원 할인');

INSERT INTO coupons (name, description, type, discount_rate, discount_amount, min_order_price, max_discount_amount, expired_at)
SELECT '생일 축하 쿠폰 5000원', '생일 축하 정액 5천원 할인', 'AMOUNT', NULL, 5000, 0, NULL, DATE_ADD(NOW(), INTERVAL 14 DAY) FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM coupons WHERE name='생일 축하 쿠폰 5000원');

-- (선택 권장)
-- ALTER TABLE user_coupons ADD UNIQUE KEY uk_user_coupons (user_id, coupon_id);
-- ALTER TABLE points       ADD UNIQUE KEY uk_points_user    (user_id);

-- 2) 유저에게 쿠폰 전부 지급 (중복 방지 + 유저 없으면 스킵)
INSERT INTO user_coupons (user_id, coupon_id, is_expiring_soon, created_at)
SELECT @USER_ID, c.id, 0, NOW()
FROM coupons c
WHERE @USER_ID IS NOT NULL
  AND c.name IN ('10% 할인','가입 축하 쿠폰 20%','10000원 할인','생일 축하 쿠폰 5000원')
  AND NOT EXISTS (
        SELECT 1 FROM user_coupons uc
        WHERE uc.user_id=@USER_ID AND uc.coupon_id=c.id
    );

-- 3) 적립금 5,000 (여러 번 실행해도 1회만)
-- (0) 타입 체크 제약 (이미 있으면 스킵)
SET @schema := DATABASE();
SET @exists := (
  SELECT COUNT(*) FROM information_schema.TABLE_CONSTRAINTS
  WHERE CONSTRAINT_SCHEMA=@schema AND TABLE_NAME='point_histories' AND CONSTRAINT_NAME='chk_point_histories_type'
);
SET @sql := IF(@exists=0,
  'ALTER TABLE point_histories ADD CONSTRAINT chk_point_histories_type CHECK (type IN (''CHARGE'',''USE'',''EXPIRE'',''REFUND''))',
  'SELECT 1');
PREPARE s FROM @sql; EXECUTE s; DEALLOCATE PREPARE s;

-- (1) points row 없으면 0원으로 생성
INSERT INTO points (user_id, total_amount, updated_at)
SELECT @USER_ID, 0, NOW()
    WHERE @USER_ID IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM points WHERE user_id=@USER_ID);

-- (2) point_id 확보
SELECT id INTO @POINT_ID FROM points WHERE user_id=@USER_ID LIMIT 1;

-- (3) ‘신규가입 적립금 5,000’ CHARGE 이력이 없으면 +5,000
UPDATE points
SET total_amount = total_amount + 5000,
    updated_at   = NOW()
WHERE id = @POINT_ID
  AND NOT EXISTS (
        SELECT 1 FROM point_histories
        WHERE point_id=@POINT_ID AND type='CHARGE' AND description='신규가입 적립금 5,000'
    );

-- (4) 이력 기록 (CHARGE, 중복 방지)
INSERT INTO point_histories (point_id, type, amount, description, created_at, expired_at)
SELECT @POINT_ID, 'CHARGE', 5000, '신규가입 적립금 5,000', NOW(), DATE_ADD(NOW(), INTERVAL 365 DAY)
FROM DUAL
WHERE @POINT_ID IS NOT NULL
  AND NOT EXISTS (
        SELECT 1 FROM point_histories
        WHERE point_id=@POINT_ID AND type='CHARGE' AND description='신규가입 적립금 5,000'
    );
