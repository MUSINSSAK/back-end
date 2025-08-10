-- 1) 컬럼 추가 (우선 NULL 허용)
ALTER TABLE categories
    ADD COLUMN slug VARCHAR(50);

-- 2) 기존 데이터에 슬러그 채우기 (네가 쓰는 프론트 슬러그 매핑)
UPDATE categories SET slug='beauty'       WHERE name='뷰티';
UPDATE categories SET slug='shoes'        WHERE name='신발';
UPDATE categories SET slug='tops'         WHERE name='상의';
UPDATE categories SET slug='outerwear'    WHERE name='아우터';
UPDATE categories SET slug='pants'        WHERE name='바지';
UPDATE categories SET slug='dresses'      WHERE name='원피스/스커트';
UPDATE categories SET slug='bags'         WHERE name='가방';
UPDATE categories SET slug='accessories'  WHERE name='패션소품';
UPDATE categories SET slug='loungewear'   WHERE name='속옷/홈웨어';
UPDATE categories SET slug='sports'       WHERE name='스포츠/레저';
UPDATE categories SET slug='digital'      WHERE name='디지털/라이프';
UPDATE categories SET slug='kids'         WHERE name='키즈';

-- 3) NOT NULL + UNIQUE 제약 (데이터 채운 뒤 제약 걸기)
ALTER TABLE categories
    MODIFY slug VARCHAR(50) NOT NULL;

CREATE UNIQUE INDEX ux_categories_slug ON categories(slug);
