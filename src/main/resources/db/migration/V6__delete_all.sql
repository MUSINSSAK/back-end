DELETE FROM product_images;
delete FROM product_options;
delete FROM products;
DELETE FROM brands;

-- 각 테이블의 AUTO_INCREMENT 값을 1로 초기화합니다.
ALTER TABLE musinsa_shop.brands AUTO_INCREMENT = 1;
ALTER TABLE musinsa_shop.products AUTO_INCREMENT = 1;
ALTER TABLE musinsa_shop.product_options AUTO_INCREMENT = 1;
ALTER TABLE musinsa_shop.product_images AUTO_INCREMENT = 1;