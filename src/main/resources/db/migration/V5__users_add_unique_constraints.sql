-- V5__users_add_unique_constraints.sql
-- 목적: 이메일 컬럼에 UNIQUE 제약을 추가하여 DB 차원에서 중복 방지
-- 주의:
--  1) 현재 테이블에 이메일 중복 데이터가 존재하면 이 마이그레이션은 실패합니다.
--     적용 전, 중복 여부를 점검하세요.
--       예) SELECT email, COUNT(*) c FROM users GROUP BY email HAVING c > 1;
--  2) MySQL의 UNIQUE는 NULL을 여러 개 허용합니다.
--     이메일은 로그인 ID이므로 NULL을 허용하지 않도록 NOT NULL로 설정하는 것이 권장됩니다.
--  3) 닉네임은 이름 성격이므로 중복을 허용합니다. 따라서 UNIQUE 제약을 추가하지 않습니다.

ALTER TABLE users
    ADD CONSTRAINT uk_users_email UNIQUE (email);
--     ADD CONSTRAINT uk_users_nickname UNIQUE (nickname);