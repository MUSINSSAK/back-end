-- (옵션/개발) 테이블이 비어있을 때만 리셋
-- ALTER TABLE musinsa_shop.addresses AUTO_INCREMENT = 1;

-- 1) 생성/수정일 자동 기록(INSERT 시 NOW, UPDATE 시 updated_at 자동 반영)
ALTER TABLE musinsa_shop.addresses
    MODIFY created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    MODIFY updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- 2) 샘플 기본 배송지 1건 삽입 (user_id=1이 존재해야 성공)
INSERT INTO musinsa_shop.addresses (
    user_id, label, recipient, phone,
    address, detail_address, postal_code, is_default
) VALUES (
    1, '집', '신범준', '010-3873-6180',
    '서울특별시 강남구 테헤란로 123', '101호', '06234', 1
);