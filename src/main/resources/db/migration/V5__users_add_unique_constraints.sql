-- V5__users_add_unique_constraints.sql
-- 목적: 이메일/닉네임 컬럼에 UNIQUE 제약을 추가하여 DB 차원 중복 방지
-- 주의:
--  1) 현재 테이블에 중복 데이터가 존재하면 이 마이그레이션은 실패합니다.
--     적용 전, 중복 여부를 점검하세요.
--       예) SELECT email, COUNT(*) c FROM users GROUP BY email HAVING c > 1;
--           SELECT nickname, COUNT(*) c FROM users GROUP BY nickname HAVING c > 1;
--  2) MySQL의 UNIQUE는 NULL을 여러 개 허용합니다.
--     닉네임을 반드시 고유하게 강제하려면 이후 단계에서 NOT NULL도 함께 적용할 예정입니다.

ALTER TABLE users
    ADD CONSTRAINT uk_users_email UNIQUE (email),
    ADD CONSTRAINT uk_users_nickname UNIQUE (nickname);