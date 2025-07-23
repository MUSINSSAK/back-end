
-- 도커에만 실행된다
-- 사용자 생성 및 권한 설정
-- CREATE USER IF NOT EXISTS 'MUSINSSAK'@'%' IDENTIFIED BY '1234';
-- GRANT ALL PRIVILEGES ON musinsa_shop.* TO 'MUSINSSAK'@'%';
-- FLUSH PRIVILEGES;

-- 테이블이 이미 있으면 삭제 (개발용)
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

-- 유저 테이블
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 상품 테이블
CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price INT NOT NULL,
                          stock INT NOT NULL,
                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 초기 유저 삽입
INSERT INTO users (email, password, name)
VALUES
    ('admin@musinsa.com', '1234', '관리자');
--     ('user1@musinsa.com', '1234', '홍길동');

-- 초기 상품 삽입
INSERT INTO products (name, description, price, stock)
VALUES
    ('무지 반팔 티셔츠', '기본 무지티, 여름 필수템', 19000, 100),
    ('데님 팬츠', '슬림 핏 데님 팬츠', 39000, 50);
