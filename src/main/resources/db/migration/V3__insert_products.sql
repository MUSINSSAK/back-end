-- brands
INSERT INTO musinsa_shop.brands (name) VALUES
('푸마'),
('질바이질스튜어트'),
('아디다스');


-- products
INSERT INTO musinsa_shop.products (
    brand_id, category_id, name, thumbnail_image_url, original_price,
    discount_rate, discounted_price, description, features, materials, care,
    created_at, updated_at
) VALUES
-- 시부이 뮬 (푸마)
(1, 2, '시부이 뮬 (39488302)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250516/5120449/5120449_17473761099761_500.jpg',
59000, 10, 53100, '시크한 스타일의 뮬 샌들',
JSON_ARRAY('여성용', '2025 시즌'),
JSON_OBJECT('upper', JSON_ARRAY('합성 피혁'), 'lining', JSON_ARRAY('폴리에스터'), 'outsole', JSON_ARRAY('고무')),
JSON_ARRAY('손세탁 권장'), NOW(), NOW()
),
-- 멜로우 샌들 (질바이질스튜어트)
(2, 2, '[멜로우] 스포티 플랫폼 샌들 - 2color', 'https://image.msscdn.net/thumbnails/images/goods_img/20250402/4976841/4976841_17502313917748_500.jpg',
81000, 10, 72900, '캐주얼한 멀티 플랫폼 샌들',
JSON_ARRAY('여성용', '2025 SS 시즌'),
JSON_OBJECT('upper', JSON_ARRAY('패브릭'), 'lining', JSON_ARRAY('합성고무'), 'outsole', JSON_ARRAY('고무')),
JSON_ARRAY('표백제 금지', '자연 건조'), NOW(), NOW()
),
-- 아딜렛 22 XLG (아디다스)
(3, 2, '아딜렛 22 XLG - 브라운 / IE5648', 'https://image.msscdn.net/thumbnails/images/goods_img/20240226/3899439/3899439_17090044948397_500.jpg',
42990, 5, 40840, '아디다스의 아딜렛 22는 슬리퍼 스타일로, 일상과 여행에 모두 어울리는 아이템입니다.',
JSON_ARRAY('편안한 착화감', '슬립온 타입', '도톰한 밑창으로 충격 완화'),
JSON_OBJECT('upper', JSON_ARRAY('EVA'), 'lining', JSON_ARRAY('텍스타일'), 'outsole', JSON_ARRAY('EVA')),
JSON_ARRAY('기계 세탁 가능'), NOW(), NOW());


-- 시부이 뮬 (푸마)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(1, 'https://image.msscdn.net/thumbnails/images/goods_img/20250516/5120449/5120449_17473761099761_500.jpg', 1),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761133976_500.jpg', 2),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761155062_500.jpg', 3),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761181758_500.jpg', 4),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761202121_500.jpg', 5),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761222147_500.jpg', 6),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761245046_500.jpg', 7);

-- [멜로우] 스포티 플랫폼 샌들 (질바이질스튜어트)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(2, 'https://image.msscdn.net/thumbnails/images/goods_img/20250402/4976841/4976841_17502313917748_500.jpg', 1),
(2, 'https://image.msscdn.net/thumbnails/images/prd_img/20250402/4976841/detail_4976841_17435618539029_500.jpg', 2);

-- 아딜렛 22 XLG - 브라운 (아디다스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(3, 'https://image.msscdn.net/thumbnails/images/goods_img/20240226/3899439/3899439_17090044948397_500.jpg', 1),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045003219_500.jpg', 2),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045009964_500.jpg', 3),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045016971_500.jpg', 4),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045024315_500.jpg', 5),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045033813_500.jpg', 6),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045044249_500.jpg', 7),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045061318_500.jpg', 8);


-- 신발이기 때문에 가상의 데이터 알맞게 넣음
INSERT INTO musinsa_shop.product_options (product_id, size, stock, price) VALUES
(1, '230', 15, 53100), (1, '240', 20, 53100), (1, '250', 12, 53100),
(2, '230', 18, 72900), (2, '240', 22, 72900), (2, '250', 17, 72900),
(3, '230', 25, 103200), (3, '240', 30, 103200), (3, '250', 19, 103200);