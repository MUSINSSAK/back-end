-- 목적: product_questions : product_question_answers 를 1:1로 강제하기 위해
--       question_id 에 UNIQUE 제약과 NOT NULL 제약을 추가
-- 이유: 질문당 답변은 무조건 1개라는 도메인 규칙을 DB 레벨에서 보장하고,
--       JPA @OneToOne(unique = true) 와 스키마를 일치시키기 위함.

-- 1) question_id는 반드시 존재해야 함 (NULL 금지)
ALTER TABLE product_question_answers
    MODIFY question_id BIGINT NOT NULL;

-- 2) 질문 1개당 답변 1개만 가능하도록 UNIQUE 제약 추가
ALTER TABLE product_question_answers
    ADD CONSTRAINT uq_product_question_answers_question_id UNIQUE (question_id);