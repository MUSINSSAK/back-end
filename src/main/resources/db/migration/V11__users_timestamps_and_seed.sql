-- 목적:
--  1) created_at / updated_at 에 DB 기본값을 설정하여 자동 기록
--  2) 기존 행에 NULL 이 있으면 NOW()로 백필
--  3) 샘플 사용자 1명 시드 (비밀번호는 BCrypt 해시 사용)

-- 0) 닉네임이 중복 허용 정책이라면 UNIQUE 제약이 남아있지 않은지 확인
--    (남아있다면 주석 해제 후 실행)
-- ALTER TABLE users DROP INDEX uk_users_nickname;

-- 1) 기존 NULL 타임스탬프 백필
UPDATE users
SET
    created_at = COALESCE(created_at, NOW()),
    updated_at = COALESCE(updated_at, NOW());

-- 2) 컬럼 정의: NOT NULL + 기본값/자동 갱신
ALTER TABLE users
    MODIFY created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    MODIFY updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- 3) (옵션) 조회/정렬을 위한 인덱스
-- CREATE INDEX idx_users_created_at ON users (created_at);

-- 4) 샘플 사용자 시드
--    비밀번호(평문): admin1234!
--    BCrypt(10라운드) 해시: $2b$10$/.3kwQ/keoqd5hW9Sp2ZD.wuzb2eON14v/AFdxMHKvI3nfZbh4O/W
--    *원하면 이메일/닉네임/생일/폰번호만 바꾸세요.
INSERT INTO users (
    email, password, nickname, phone, birth_date,
    profile_image_url, last_password_modified_at
) VALUES (
    'sinbumjun123@naver.com',
    '$2b$10$/.3kwQ/keoqd5hW9Sp2ZD.wuzb2eON14v/AFdxMHKvI3nfZbh4O/W',
    '신범준',
    '010-3873-6180',
    '1995-10-05',
    NULL,
    NOW()
);

-- created_at / updated_at 은 위 ALTER 로 인해 자동으로 NOW()가 입력됩니다.