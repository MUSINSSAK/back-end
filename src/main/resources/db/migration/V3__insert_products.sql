-- brands
INSERT INTO musinsa_shop.brands (name) VALUES
('기호'),
('나이키'),
('노스페이스'),
('누스'),
('뉴발란스'),
('다이나핏'),
('닥터마틴'),
('드로우핏'),
('락피쉬웨더웨어'),
('레이지앤'),
('로맨틱무브'),
('리복'),
('마뗑킴'),
('마크모크'),
('모그어스'),
('반스'),
('발렌시아가'),
('비에스큐티바이클래시'),
('사뿐'),
('생로랑'),
('세스띠'),
('세인트새틴'),
('솔트앤초콜릿'),
('슈마커'),
('슈펜'),
('슈피트'),
('식스핏'),
('아디다스'),
('아식스'),
('알뜨알레'),
('야세'),
('엄브로'),
('엘리자베스 스튜어트'),
('엘칸토'),
('엠엘비'),
('예루살렘 샌들'),
('위더로드'),
('잔카'),
('질바이질스튜어트'),
('착한구두'),
('츄바스코'),
('커스텀에이드 우먼'),
('컨버스'),
('케즈'),
('코모레비뮤지엄'),
('타크트로이메'),
('팀버랜드'),
('파스코로젠'),
('푸마'),
('프리플라'),
('플로리다 스튜디오'),
('피렌체 아뜨리에'),
('헌터'),
('험토'),
('호카');


-- products
INSERT INTO musinsa_shop.products (
  brand_id, category_id, name, thumbnail_image_url, original_price,
  discount_rate, discounted_price,
  created_at, updated_at
) VALUES
-- 시부이 뮬 (푸마)
(49, 2, '시부이 뮬 (39488302)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250516/5120449/5120449_17473761099761_500.jpg', 59000, 20, 47200, NOW(), NOW()),
-- [멜로우] 스포티 플랫폼 샌들 - 2color (질바이질스튜어트)
(39, 2, '[멜로우] 스포티 플랫폼 샌들 - 2color', 'https://image.msscdn.net/thumbnails/images/goods_img/20250402/4976841/4976841_17502313917748_500.jpg', 81000, 0, 81000, NOW(), NOW()),
-- 아딜렛 22 XLG - 브라운 / IE5648 (아디다스)
(28, 2, '아딜렛 22 XLG - 브라운 / IE5648', 'https://image.msscdn.net/thumbnails/images/goods_img/20240226/3899439/3899439_17090044948397_500.jpg', 42990, 15, 36540, NOW(), NOW()),
-- GO46 바라스 아웃도어 캐니언 샌들 (비에스큐티바이클래시)
(18, 2, 'GO46 바라스 아웃도어 캐니언 샌들', 'https://image.msscdn.net/thumbnails/images/goods_img/20250425/5067624/5067624_17458239723822_500.jpg', 56000, 0, 56000, NOW(), NOW()),
-- 에이미 샌들 (케즈)
(44, 2, '에이미 샌들(5SM02602H001)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250523/5143402/5143402_17479837103908_500.jpg', 69000, 0, 69000, NOW(), NOW()),
-- Emerald-003 - 그물 스트랩 플랫 샌들 (타크트로이메)
(46, 2, 'Emerald-003 - 그물 스트랩 플랫 샌들 (에메랄드-003)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240514/4129020/4129020_17156701759638_500.jpg', 72000, 25, 54000, NOW(), NOW()),
-- GO76 리베카 루프 샌들 (비에스큐티바이클래시)
(18, 2, 'GO76 리베카 루프 샌들', 'https://image.msscdn.net/thumbnails/images/goods_img/20250619/5194897/5194897_17510076994678_500.jpg', 39840, 10, 35860, NOW(), NOW()),
-- [WOMEN] 트래블 플로우 샌들 - 블랙 UFF7103MSHBLK (헌터)
(53, 2, '[WOMEN] 트래블 플로우 샌들 - 블랙 UFF7103MSHBLK', 'https://image.msscdn.net/thumbnails/images/goods_img/20250310/4875489/4875489_17512600561658_500.jpg', 149000, 15, 126650, NOW(), NOW()),
-- NBRJFS480B / SD7401BK (뉴발란스)
(5, 2, 'NBRJFS480B / SD7401BK (BLACK)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250617/5189617/5189617_17510108508472_500.jpg', 89000, 30, 62300, NOW(), NOW()),
-- RONDA CS2005 ALL BLACK (츄바스코)
(41, 2, 'RONDA CS2005 ALL BLACK', 'https://image.msscdn.net/thumbnails/images/goods_img/20210427/1923386/1923386_5_500.jpg', 55000, 30, 38500, NOW(), NOW()),
-- 웨이브뮬 (푸마)
(49, 2, '웨이브뮬 (39905002)', 'https://image.msscdn.net/thumbnails/images/goods_img/20241104/4584275/4584275_17306825087054_500.jpg', 23600, 30, 16520, NOW(), NOW()),
-- PLUSH SANDAL (엄브로)
(32, 2, 'PLUSH SANDAL (플러쉬 샌들) 화이트(UQ223ESA43)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250321/4929164/4929164_17515024602583_500.jpg', 89000, 15, 75650, NOW(), NOW()),
-- 19B503 black loafer (프리플라)
(50, 2, '19B503 black loafer', 'https://image.msscdn.net/thumbnails/images/goods_img/20190909/1147638/1147638_1_500.jpg', 89000, 25, 66750, NOW(), NOW()),
-- 웨버 싸이클론 (슈마커)
(24, 2, '웨버 싸이클론 (0093859) WV24002 BB', 'https://image.msscdn.net/thumbnails/images/goods_img/20240521/4145238/4145238_17162726026596_500.jpg', 33750, 15, 28690, NOW(), NOW()),
-- 여성 하이킹 샌들 제트론 HGC (험토)
(54, 2, '여성 하이킹 샌들 제트론 HGC', 'https://image.msscdn.net/thumbnails/images/goods_img/20240311/3939622/3939622_17473773508069_500.png', 88900, 0, 88900, NOW(), NOW()),
-- [클로이] 블랙 스퀘어 샌들 (질바이질스튜어트)
(39, 2, '[클로이] 블랙 스퀘어 샌들', 'https://image.msscdn.net/thumbnails/images/goods_img/20240426/4091326/4091326_17446794688862_500.jpg', 79000, 20, 63200, NOW(), NOW()),
-- 트리플 버클 슬링백 4cm (슈펜)
(25, 2, '트리플 버클 슬링백 4cm (HPWWVFA101 RE) HPWWVFS404', 'https://image.msscdn.net/thumbnails/images/goods_img/20250526/5146320/5146320_17482413544611_500.jpg', 39900, 25, 29920, NOW(), NOW()),
-- 이지 로프 샌들 블랙 (기호)
(1, 2, '이지 로프 샌들 블랙', 'https://image.msscdn.net/thumbnails/images/goods_img/20250414/5025539/5025539_17457284972375_500.jpg', 64350, 30, 45040, NOW(), NOW()),
-- 메건 스틸레토 슬링백 [n098]_2color (누스)
(4, 2, '메건 스틸레토 슬링백 [n098]_2color', 'https://image.msscdn.net/thumbnails/images/goods_img/20250327/4952219/4952219_17430539501372_500.jpg', 63000, 25, 47250, NOW(), NOW()),
-- 그루피 샌들 - 블랙:골드 / 722309WBED11080 (발렌시아가)
(17, 2, '그루피 샌들 - 블랙:골드 / 722309WBED11080', 'https://image.msscdn.net/thumbnails/images/goods_img/20230330/3192376/3192376_16861916518887_500.jpg', 366990, 10, 330290, NOW(), NOW()),
-- 인텐스 남성 스퀘어 페니 로퍼 드레스 3cm LCMD35I513 (엘칸토)
(34, 2, '인텐스 남성 스퀘어 페니 로퍼 드레스 3cm LCMD35I513', 'https://image.msscdn.net/thumbnails/images/goods_img/20250211/4774305/4774305_17399228067273_500.jpg', 39100, 20, 31280, NOW(), NOW()),
-- 하이페리엄 슬라이드 - 베이지 / 100251039 (리복)
(12, 2, '하이페리엄 슬라이드 - 베이지 / 100251039', 'https://image.msscdn.net/thumbnails/images/goods_img/20250423/5055516/5055516_17453751844870_500.jpg', 69000, 30, 48300, NOW(), NOW()),
--  (호카)
(55, 2, '(남성) 호파라 2 - 블랙:블랙 / 1147650-BBLC', 'https://image.msscdn.net/thumbnails/images/goods_img/20240130/3833073/3833073_17078745794650_500.jpg', 179000, 30, 125300, NOW(), NOW()),
-- 보스 쿼드 블랙 하이드로 샌들 / 26725001 (닥터마틴)
(7, 2, '보스 쿼드 블랙 하이드로 샌들 / 26725001', 'https://image.msscdn.net/thumbnails/images/goods_img/20210223/1810765/1810765_1_500.jpg', 132990, 20, 106390, NOW(), NOW()),
-- 쥬아나 피셔맨 샌들 (사뿐)
(19, 2, '쥬아나 피셔맨 샌들 (1cm)', 'https://image.msscdn.net/thumbnails/images/goods_img/20230510/3286833/3286833_17169489635065_500.jpg', 44910, 0, 44910, NOW(), NOW()),
-- 1YD XSLIDE (식스핏)
(27, 2, '1YD XSLIDE', 'https://image.msscdn.net/thumbnails/images/goods_img/20240523/4150123/4150123_17170446982899_500.jpg', 21000, 20, 16800, NOW(), NOW()),
-- [슈퍼세일]센트리 올드스쿨 WC - (반스)
(16, 2, '[슈퍼세일]센트리 올드스쿨 WC - (셰르파 스웨이드) 블랙 / VN0A4BVMBLK1', 'https://image.msscdn.net/thumbnails/images/goods_img/20221005/2839678/2839678_1_500.jpg', 34990, 15, 29740, NOW(), NOW()),
-- 리본 메쉬 샌들 (슈펜)
(25, 2, '리본 메쉬 샌들 (3cm) HPWWNFS601', 'https://image.msscdn.net/thumbnails/images/goods_img/20250513/5106883/5106883_17471127170642_500.jpg', 39900, 20, 31920, NOW(), NOW()),
-- [리퍼브] KM223 핸드메이드 카우레더 아치 스트랩 플랫폼 샌들 천연가죽 ver. l 5Colors (코모레비뮤지엄)
(45, 2, '[리퍼브] KM223 핸드메이드 카우레더 아치 스트랩 플랫폼 샌들 천연가죽 ver. l 5Colors', 'https://image.msscdn.net/thumbnails/images/goods_img/20250617/5191123/5191123_17501480976946_500.jpg', 39000, 30, 27300, NOW(), NOW()),
-- GO77 릭센 파이어컷 샌들 (비에스큐티바이클래시)
(18, 2, 'GO77 릭센 파이어컷 샌들', 'https://image.msscdn.net/thumbnails/images/goods_img/20250619/5194896/5194896_17510076605169_500.jpg', 39840, 20, 31870, NOW(), NOW()),
-- 청키샌들 NY (엠엘비)
(35, 2, '청키샌들 NY (Black)', 'https://image.msscdn.net/thumbnails/images/goods_img/20230331/3197910/3197910_17201543122829_500.jpg', 39500, 15, 33580, NOW(), NOW()),
-- [리퍼브] KM069 핸드메이드 카우레더 청키 솔 피셔맨 샌들 6cm I 4Colors (코모레비뮤지엄)
(45, 2, '[리퍼브] KM069 핸드메이드 카우레더 청키 솔 피셔맨 샌들 6cm I 4Colors', 'https://image.msscdn.net/thumbnails/images/goods_img/20250618/5192357/5192357_17502165659696_500.jpg', 39000, 25, 29250, NOW(), NOW()),
-- 엑시드 스트랩 레더 샌들 [BLACK] (드로우핏)
(8, 2, '엑시드 스트랩 레더 샌들 [BLACK]', 'https://image.msscdn.net/thumbnails/images/goods_img/20210429/1928038/1928038_17448601585397_500.jpg', 75200, 25, 56400, NOW(), NOW()),
-- 인텐스 여성 트렌디 리본 플랫 샌들 2cm LCWW61I526 (엘칸토)
(34, 2, '인텐스 여성 트렌디 리본 플랫 샌들 2cm LCWW61I526', 'https://image.msscdn.net/thumbnails/images/goods_img/20250325/4937924/4937924_17428588597073_500.jpg', 45360, 0, 45360, NOW(), NOW()),
-- 젤-카야노 14 - 화이트:그래파이트 그레이 / 1203A537-110 (아식스)
(29, 2, '젤-카야노 14 - 화이트:그래파이트 그레이 / 1203A537-110', 'https://image.msscdn.net/thumbnails/images/goods_img/20250630/5213548/5213548_17512631003915_500.jpg', 179000, 25, 134250, NOW(), NOW()),
-- 노트 스트링 플랫폼 샌들 (누스)
(4, 2, '노트 스트링 플랫폼 샌들(남여공용)[n5212]', 'https://image.msscdn.net/thumbnails/images/goods_img/20230523/3318485/3318485_16889735961381_500.jpg', 53400, 15, 45390, NOW(), NOW()),
-- BUCKLE EYELET SLIPPERS IN BLACK (마뗑킴)
(13, 2, 'BUCKLE EYELET SLIPPERS IN BLACK', 'https://image.msscdn.net/thumbnails/images/goods_img/20250526/5145781/5145781_17482351019795_500.jpg', 48000, 20, 38400, NOW(), NOW()),
-- [WOMEN] 시티 레블 네오 레더 버클 샌들 - 블랙 UFD3109LEABLK (헌터)
(53, 2, '[WOMEN] 시티 레블 네오 레더 버클 샌들 - 블랙 UFD3109LEABLK', 'https://image.msscdn.net/thumbnails/images/goods_img/20250401/4973538/4973538_17440736223938_500.jpg', 122430, 10, 110190, NOW(), NOW()),
-- 루아 투웨이 컴포트 샌들 (착한구두)
(40, 2, '루아 투웨이 컴포트 샌들 (2TYPE) (크로스,라인)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250417/5038724/5038724_17448676710425_500.jpg', 25110, 0, 25110, NOW(), NOW()),
-- 아딜렛 22 - 다크그레이 / HP6522 (아디다스)
(28, 2, '아딜렛 22 - 다크그레이 / HP6522', 'https://image.msscdn.net/thumbnails/images/goods_img/20230426/3262218/3262218_16830140039641_500.jpg', 34990, 20, 27990, NOW(), NOW()),
-- 루즈핏 하프부츠 HA2419 (알뜨알레)
(30, 2, '루즈핏 하프부츠 HA2419', 'https://image.msscdn.net/thumbnails/images/goods_img/20240919/4445577/4445577_17267343747007_500.jpg', 83300, 15, 70800, NOW(), NOW()),
-- 아린스 에나멜 슬링백힐 (사뿐)
(19, 2, '아린스 에나멜 슬링백힐 (5/7cm)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240207/3852562/3852562_17072705457736_500.jpg', 46020, 20, 36820, NOW(), NOW()),
-- 에이미 샌들 (케즈)
(44, 2, '에이미 샌들(5SM02602H920)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250523/5143404/5143404_17479837517151_500.jpg', 69000, 30, 48300, NOW(), NOW()),
-- 신시아 메리제인 클로그 2color (모그어스)
(15, 2, '신시아 메리제인 클로그 2color', 'https://image.msscdn.net/thumbnails/images/goods_img/20250508/5093449/5093449_17466725932317_500.jpg', 33000, 25, 24750, NOW(), NOW()),
-- 츄비01 스트랩 샌들 412524006 (솔트앤초콜릿)
(23, 2, '츄비01 스트랩 샌들 412524006 (4.5cm)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250520/5129582/5129582_17477302350292_500.jpg', 69300, 15, 58900, NOW(), NOW()),
-- 트리뷰트 플랫 뮬 - 블랙 / 571952BDA001000 (생로랑)
(20, 2, '트리뷰트 플랫 뮬 - 블랙 / 571952BDA001000', 'https://image.msscdn.net/thumbnails/images/goods_img/20250224/4820676/4820676_17413143577402_500.jpg', 502990, 15, 427540, NOW(), NOW()),
-- 마쯔 여성 고프코어 캐주얼 스니커즈 4cm LCWC24M413 (엘칸토)
(34, 2, '마쯔 여성 고프코어 캐주얼 스니커즈 4cm LCWC24M413', 'https://image.msscdn.net/thumbnails/images/goods_img/20240115/3794771/3794771_17052934924402_500.jpg', 47200, 15, 40120, NOW(), NOW()),
-- FL4724 르블랑 바이커 버클 스트랩 부츠 (플로리다 스튜디오)
(51, 2, 'FL4724 르블랑 바이커 버클 스트랩 부츠', 'https://image.msscdn.net/thumbnails/images/goods_img/20241010/4503796/4503796_17297326339904_500.jpg', 79800, 25, 59850, NOW(), NOW()),
-- Loel 버클 스트랩 웨스턴 2TYPE 부츠 (레이지앤)
(10, 2, 'Loel 버클 스트랩 웨스턴 2TYPE 부츠(미들,롱)(3cm)_AOL116(M) / AOL118(L) l 3colors', 'https://image.msscdn.net/thumbnails/images/goods_img/20240806/4299698/4299698_17252456003310_500.jpg', 103000, 10, 92700, NOW(), NOW()),
-- NBRJFS440B / 브리즈 / SD2202BK2 (뉴발란스)
(5, 2, 'NBRJFS440B / 브리즈 / SD2202BK2 (BLACK)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250311/4878032/4878032_17418321777947_500.jpg', 129000, 15, 109650, NOW(), NOW()),
-- ljh9001 mules _ 3colors (프리플라)
(50, 2, 'ljh9001 mules _ 3colors', 'https://image.msscdn.net/thumbnails/images/goods_img/20210517/1953154/1953154_16812302725178_500.jpg', 55000, 10, 49500, NOW(), NOW()),
-- CHUBASCO ALMERIA CA2203 TAN (츄바스코)
(41, 2, 'CHUBASCO ALMERIA CA2203 TAN', 'https://image.msscdn.net/thumbnails/images/goods_img/20230502/3271676/3271676_17139249954464_500.jpg', 65000, 25, 48750, NOW(), NOW()),
-- 마쯔 여성 글래디에이터 청키 샌들 3cm LCWW93M526 (엘칸토)
(34, 2, '마쯔 여성 글래디에이터 청키 샌들 3cm LCWW93M526', 'https://image.msscdn.net/thumbnails/images/goods_img/20250429/5074911/5074911_17458875878051_500.jpg', 41600, 15, 35360, NOW(), NOW()),
-- [서지수 PICK]GLAIR SLINGBACK SANDALS - 6 colors (락피쉬웨더웨어)
(9, 2, '[서지수 PICK]GLAIR SLINGBACK SANDALS - 6 colors', 'https://image.msscdn.net/thumbnails/images/goods_img/20250519/5125579/5125579_17515886182924_500.jpg', 50400, 0, 50400, NOW(), NOW()),
-- Sahara 사하라 네트형 빈티지 피셔맨_3color 2cm (마크모크)
(14, 2, 'Sahara 사하라 네트형 빈티지 피셔맨_3color 2cm', 'https://image.msscdn.net/thumbnails/images/goods_img/20250408/4999183/4999183_17445919628916_500.jpg', 69300, 20, 55440, NOW(), NOW()),
-- 코듀라 비트닉 - 락 블랙 / FY2949 (리복)
(12, 2, '코듀라 비트닉 - 락 블랙 / FY2949', 'https://image.msscdn.net/thumbnails/images/goods_img/20230407/3215382/3215382_16808434025177_500.jpg', 64500, 0, 64500, NOW(), NOW()),
-- 레이넬 클래식 페니 로퍼 (착한구두)
(40, 2, '레이넬 클래식 페니 로퍼 (ENAMEL)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250404/4988986/4988986_17525654944688_500.jpg', 35900, 20, 28720, NOW(), NOW()),
-- 인텐스 여성 버클 슬링백 2cm LCWO92I513 (엘칸토)
(34, 2, '인텐스 여성 버클 슬링백 2cm LCWO92I513', 'https://image.msscdn.net/thumbnails/images/goods_img/20250110/4709327/4709327_17364718722589_500.jpg', 47100, 20, 37680, NOW(), NOW()),
-- 5.5cm 벨크로 피셔맨 플랫폼 샌들 윌라 (커스텀에이드 우먼)
(42, 2, '5.5cm 벨크로 피셔맨 플랫폼 샌들 윌라(CW0067BK)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240530/4166303/4166303_17494533353728_500.jpg', 54000, 10, 48600, NOW(), NOW()),
-- 오션 선 피쉬 샌들 (잔카)
(38, 2, '오션 선 피쉬 샌들', 'https://image.msscdn.net/thumbnails/images/goods_img/20250513/5106879/5106879_17471126550556_500.jpg', 69000, 0, 69000, NOW(), NOW()),
-- Creek - 멀티 버클 스트랩 샌들 (타크트로이메)
(46, 2, 'Creek - 멀티 버클 스트랩 샌들 (크릭)', 'https://image.msscdn.net/thumbnails/images/goods_img/20220704/2645639/2645639_17476235629665_500.jpg', 68400, 30, 47880, NOW(), NOW()),
-- 남성 프리미엄 베이직 부츠 위트브라운_TB1180942311 (팀버랜드)
(47, 2, '남성 프리미엄 베이직 부츠 위트브라운_TB1180942311', 'https://image.msscdn.net/thumbnails/images/goods_img/20240809/4313969/4313969_17231729917740_500.jpg', 180600, 20, 144480, NOW(), NOW()),
-- 남성 시톤 베이 슬라이드 탄_TB0A2422F131 (팀버랜드)
(47, 2, '남성 시톤 베이 슬라이드 탄_TB0A2422F131', 'https://image.msscdn.net/thumbnails/images/goods_img/20250429/5077402/5077402_17459086748520_500.jpg', 54000, 10, 48600, NOW(), NOW()),
-- [리버] 블랙 플랫폼 로퍼 (질바이질스튜어트)
(39, 2, '[리버] 블랙 플랫폼 로퍼', 'https://image.msscdn.net/thumbnails/images/goods_img/20230116/3027317/3027317_16806691735937_500.jpg', 83300, 15, 70800, NOW(), NOW()),
-- LEEDS (엄브로)
(32, 2, 'LEEDS (리즈) 블랙(UQ223ESA44)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250321/4929128/4929128_17524518314703_500.jpg', 109000, 15, 92650, NOW(), NOW()),
-- 니들워크 슬라이드 R25M700 (로맨틱무브)
(11, 2, '니들워크 슬라이드 R25M700 (SU토바코)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250417/5038328/5038328_17461480708189_500.jpg', 93000, 0, 93000, NOW(), NOW()),
-- 텐더폼 하이브_Black (다이나핏)
(6, 2, '텐더폼 하이브_Black', 'https://image.msscdn.net/thumbnails/images/goods_img/20220503/2538107/2538107_1_500.jpg', 25000, 30, 17500, NOW(), NOW()),
-- SECOND.A 더블라인 베이직 레더샌들 2NDS010B [BLACK] (피렌체 아뜨리에)
(52, 2, 'SECOND.A 더블라인 베이직 레더샌들 2NDS010B [BLACK]', 'https://image.msscdn.net/thumbnails/images/goods_img/20210421/1912404/1912404_17171558467914_500.jpg', 55500, 10, 49950, NOW(), NOW()),
-- 클라우드 메리제인 (케즈)
(44, 2, '클라우드 메리제인(5SM01968G920)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240118/3806869/3806869_17114398569826_500.jpg', 41300, 15, 35100, NOW(), NOW()),
-- 젤-1090 W - 화이트:미드나이트 / 1022A215-100 (아식스)
(29, 2, '젤-1090 W - 화이트:미드나이트 / 1022A215-100', 'https://image.msscdn.net/thumbnails/images/goods_img/20200207/1296778/1296778_2_500.jpg', 99000, 0, 99000, NOW(), NOW()),
-- 92BNN ELAN BUCKLE-BROWN (예루살렘 샌들)
(36, 2, '92BNN ELAN BUCKLE-BROWN (엘란 버클)', 'https://image.msscdn.net/thumbnails/images/goods_img/20250314/4898245/4898245_17453702096468_500.jpg', 103200, 20, 82560, NOW(), NOW()),
-- 에이스 - 블랙 / 31530001 (닥터마틴)
(7, 2, '에이스 - 블랙 / 31530001', 'https://image.msscdn.net/thumbnails/images/goods_img/20240319/3966132/3966132_17119597642348_500.jpg', 74990, 25, 56240, NOW(), NOW()),
-- WTRD4607 올오버 위브 샌들 블랙 [230~290MM] (위더로드)
(37, 2, 'WTRD4607 올오버 위브 샌들 블랙 [230~290MM]', 'https://image.msscdn.net/thumbnails/images/goods_img/20230503/3273723/3273723_17198208392104_500.jpg', 48900, 10, 44010, NOW(), NOW()),
-- 시부이 뮬 - 아이보리 / 394883-03 (푸마)
(49, 2, '시부이 뮬 - 아이보리 / 394883-03', 'https://image.msscdn.net/thumbnails/images/goods_img/20230411/3222874/3222874_16820518256946_500.jpg', 40990, 10, 36890, NOW(), NOW()),
-- 에센셜 슬라이드 블랙 A12174C (컨버스)
(43, 2, '에센셜 슬라이드 블랙 A12174C', 'https://image.msscdn.net/thumbnails/images/goods_img/20250311/4879732/4879732_17416760013561_500.jpg', 23400, 0, 23400, NOW(), NOW()),
-- GO45 로크린 메쉬 스포츠 샌들 (비에스큐티바이클래시)
(18, 2, 'GO45 로크린 메쉬 스포츠 샌들', 'https://image.msscdn.net/thumbnails/images/goods_img/20250425/5067626/5067626_17458239380079_500.jpg', 78500, 15, 66720, NOW(), NOW()),
-- NBPFFF750L / W480SK5 (뉴발란스)
(5, 2, 'NBPFFF750L / W480SK5 (Uni 4E) (BLACK)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240226/3901126/3901126_17479644701162_500.jpg', 99000, 0, 99000, NOW(), NOW()),
-- 마쯔 여성 토캡 메리제인 캐주얼 스니커즈 4.5cm LCWC90M513 (엘칸토)
(34, 2, '마쯔 여성 토캡 메리제인 캐주얼 스니커즈 4.5cm LCWC90M513', 'https://image.msscdn.net/thumbnails/images/goods_img/20250116/4723567/4723567_17388077451839_500.jpg', 49560, 10, 44600, NOW(), NOW()),
-- CLARO (엄브로)
(32, 2, 'CLARO (클레로) 화이트(UP123CDR20)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240205/3845822/3845822_17310353621495_500.jpg', 51600, 15, 43860, NOW(), NOW()),
-- 푸마 투리노 II SD - 그레이:오크 / 397453-03 (푸마)
(49, 2, '푸마 투리노 II SD - 그레이:오크 / 397453-03', 'https://image.msscdn.net/thumbnails/images/goods_img/20241008/4495201/4495201_17288886049288_500.jpg', 61990, 30, 43390, NOW(), NOW()),
-- 레이첼 라운드 스트랩 샌들 (사뿐)
(19, 2, '레이첼 라운드 스트랩 샌들 (2cm)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240429/4095283/4095283_17143793829256_500.jpg', 40390, 10, 36350, NOW(), NOW()),
-- [가드너] 스퀘어메리제인 슬링백 2.5 HPWWVD201S (슈펜)
(25, 2, '[가드너] 스퀘어메리제인 슬링백 2.5 HPWWVD201S', 'https://image.msscdn.net/thumbnails/images/goods_img/20230314/3144895/3144895_16787545241725_500.jpg', 19900, 30, 13930, NOW(), NOW()),
-- [워터 리지스턴트] 콤피 스트링 샌들 412424003 (솔트앤초콜릿)
(23, 2, '[워터 리지스턴트] 콤피 스트링 샌들 412424003 (3cm/2colors)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240517/4138411/4138411_17502317227285_500.jpg', 69000, 15, 58650, NOW(), NOW()),
-- 셔링 스트랩 플랫폼 샌들 (세스띠)
(21, 2, '셔링 스트랩 플랫폼 샌들 (2COLOR)', 'https://image.msscdn.net/thumbnails/images/goods_img/20230522/3314501/3314501_16847329100006_500.jpg', 73450, 15, 62430, NOW(), NOW()),
-- CHUBASCO NUBE MEN SLIDE CB2301 ALL BLACK (츄바스코)
(41, 2, 'CHUBASCO NUBE MEN SLIDE CB2301 ALL BLACK', 'https://image.msscdn.net/thumbnails/images/goods_img/20230607/3348606/3348606_17139253178788_500.jpg', 29000, 30, 20300, NOW(), NOW()),
-- NBRJFS430I / Cove / SD4205IV2 (뉴발란스)
(5, 2, 'NBRJFS430I / Cove / SD4205IV2 (IVORY)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240411/4042912/4042912_17449388851157_500.jpg', 129000, 20, 103200, NOW(), NOW()),
-- 스티치 포인트 샌들 EBAL157318 (엘리자베스 스튜어트)
(33, 2, '스티치 포인트 샌들 EBAL157318', 'https://image.msscdn.net/thumbnails/images/goods_img/20250325/4939875/4939875_17471845225986_500.jpg', 46900, 10, 42210, NOW(), NOW()),
-- 라운드 토 스티치 라이딩 롱부츠 412348003 (솔트앤초콜릿)
(23, 2, '라운드 토 스티치 라이딩 롱부츠 412348003 (4cm/1color)', 'https://image.msscdn.net/thumbnails/images/goods_img/20230830/3513077/3513077_16933678909470_500.jpg', 77400, 10, 69660, NOW(), NOW()),
-- 에어 맥스 코코 W - 블랙:메탈릭 실버 / IH6313-010 (나이키)
(2, 2, '에어 맥스 코코 W - 블랙:메탈릭 실버 / IH6313-010', 'https://image.msscdn.net/thumbnails/images/goods_img/20250610/5173985/5173985_17502272129920_500.jpg', 139000, 20, 111200, NOW(), NOW()),
-- 노마 피셔맨 샌들 [n5251]_3color (누스)
(4, 2, '노마 피셔맨 샌들 [n5251]_3color', 'https://image.msscdn.net/thumbnails/images/goods_img/20240513/4124709/4124709_17185961472758_500.jpg', 52800, 0, 52800, NOW(), NOW()),
-- MS6394 블레스 더블 스트랩 샌들 블랙 [250~280mm] (파스코로젠)
(48, 2, 'MS6394 블레스 더블 스트랩 샌들 블랙 [250~280mm]', 'https://image.msscdn.net/thumbnails/images/goods_img/20200429/1428666/1428666_20_500.jpg', 52400, 30, 36680, NOW(), NOW()),
-- 문비치 슬리퍼 R24M800 (로맨틱무브)
(11, 2, '문비치 슬리퍼 R24M800 (브라운)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240522/4147721/4147721_17473781091350_500.jpg', 46000, 20, 36800, NOW(), NOW()),
-- NS93R44J 화이트라벨 슈퍼 킥스 2 EX_IVORY (노스페이스)
(3, 2, 'NS93R44J 화이트라벨 슈퍼 킥스 2 EX_IVORY', 'https://image.msscdn.net/thumbnails/images/goods_img/20250304/4852386/4852386_17410607336635_500.jpg', 119200, 25, 89400, NOW(), NOW()),
-- 플립플랍 패브릭 쪼리 레더 슬리퍼 SFT-402 (슈피트)
(26, 2, '플립플랍 패브릭 쪼리 레더 슬리퍼 SFT-402', 'https://image.msscdn.net/thumbnails/images/goods_img/20240618/4205300/4205300_17187200600323_500.jpg', 44500, 20, 35600, NOW(), NOW()),
-- GRAMPI (엄브로)
(32, 2, 'GRAMPI (그램피) 블랙(UP223ESA41)', 'https://image.msscdn.net/thumbnails/images/goods_img/20240401/4008830/4008830_17501213171276_500.jpg', 47600, 20, 38080, NOW(), NOW()),
-- GEL-1130 - 버치:다크타우페 / 1201A995-200 (아식스)
(29, 2, 'GEL-1130 - 버치:다크타우페 / 1201A995-200', 'https://image.msscdn.net/thumbnails/images/goods_img/20250410/5010681/5010681_17442684862852_500.jpg', 119000, 25, 89250, NOW(), NOW()),
-- 여성 린덴 우즈 6인치 레이스업 부츠 위트브라운_TB1A161G2311 (팀버랜드)
(47, 2, '여성 린덴 우즈 6인치 레이스업 부츠 위트브라운_TB1A161G2311', 'https://image.msscdn.net/thumbnails/images/goods_img/20250522/5137524/5137524_17478804561614_500.jpg', 194600, 20, 155680, NOW(), NOW()),
-- 리네아 스니커즈 412430001 (솔트앤초콜릿)
(23, 2, '리네아 스니커즈 412430001 (1.5cm/3colors)', 'https://image.msscdn.net/thumbnails/images/goods_img/20241008/4497489/4497489_17283662901255_500.jpg', 79200, 25, 59400, NOW(), NOW()),
-- 산토리니 샌들 레더 블랙 552 (야세)
(31, 2, '산토리니 샌들 레더 블랙 552', 'https://image.msscdn.net/thumbnails/images/goods_img/20200424/1419869/1419869_17218696015955_500.jpg', 51800, 0, 51800, NOW(), NOW()),
-- 타버 컴포트 벨크로 피셔맨 샌들 (세인트새틴)
(22, 2, '타버 컴포트 벨크로 피셔맨 샌들 (4cm) 블랙', 'https://image.msscdn.net/thumbnails/images/goods_img/20250328/4958671/4958671_17431361442841_500.png', 54900, 30, 38430, NOW(), NOW()),
-- [슈탠다드] 논슬립 투버클 리커버리 슬리퍼 HPCV5E2502 (슈펜)
(25, 2, '[슈탠다드] 논슬립 투버클 리커버리 슬리퍼 HPCV5E2502', 'https://image.msscdn.net/thumbnails/images/goods_img/20240419/4068667/4068667_17473791769304_500.jpg', 19900, 15, 16920, NOW(), NOW()),
-- [리퍼브/단종] KM002 코모레비 뮤지엄 유니섹스 핸드메이드 레더 피셔맨 샌들 l 4Colors (코모레비뮤지엄)
(45, 2, '[리퍼브/단종] KM002 코모레비 뮤지엄 유니섹스 핸드메이드 레더 피셔맨 샌들 l 4Colors', 'https://image.msscdn.net/thumbnails/images/goods_img/20250613/5182966/5182966_17497898822642_500.jpg', 43000, 15, 36550, NOW(), NOW());


-- 시부이 뮬 (푸마)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(1, 'https://image.msscdn.net/thumbnails/images/goods_img/20250516/5120449/5120449_17473761099761_500.jpg', 1),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761133976_500.jpg', 2),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761155062_500.jpg', 3),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761181758_500.jpg', 4),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761202121_500.jpg', 5),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761222147_500.jpg', 6),
(1, 'https://image.msscdn.net/thumbnails/images/prd_img/20250516/5120449/detail_5120449_17473761245046_500.jpg', 7);

-- [멜로우] 스포티 플랫폼 샌들 - 2color (질바이질스튜어트)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(2, 'https://image.msscdn.net/thumbnails/images/goods_img/20250402/4976841/4976841_17502313917748_500.jpg', 1),
(2, 'https://image.msscdn.net/thumbnails/images/prd_img/20250402/4976841/detail_4976841_17435618539029_500.jpg', 2);

-- 아딜렛 22 XLG - 브라운 / IE5648 (아디다스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(3, 'https://image.msscdn.net/thumbnails/images/goods_img/20240226/3899439/3899439_17090044948397_500.jpg', 1),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045003219_500.jpg', 2),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045009964_500.jpg', 3),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045016971_500.jpg', 4),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045024315_500.jpg', 5),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045033813_500.jpg', 6),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045044249_500.jpg', 7),
(3, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3899439/detail_3899439_17090045061318_500.jpg', 8);

-- GO46 바라스 아웃도어 캐니언 샌들 (비에스큐티바이클래시)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(4, 'https://image.msscdn.net/thumbnails/images/goods_img/20250425/5067624/5067624_17458239723822_500.jpg', 1),
(4, 'https://image.msscdn.net/thumbnails/images/prd_img/20250425/5067624/detail_5067624_17458239811538_500.jpg', 2),
(4, 'https://image.msscdn.net/thumbnails/images/prd_img/20250425/5067624/detail_5067624_17458239844140_500.jpg', 3),
(4, 'https://image.msscdn.net/thumbnails/images/prd_img/20250425/5067624/detail_5067624_17458239879754_500.jpg', 4),
(4, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/30/f7b5c03aa80544bfacaf6e97fec51d6c.jpg', 5);

-- 에이미 샌들 (케즈)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(5, 'https://image.msscdn.net/thumbnails/images/goods_img/20250523/5143402/5143402_17479837103908_500.jpg', 1),
(5, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143402/detail_5143402_17479837113689_500.jpg', 2),
(5, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143402/detail_5143402_17479837116229_500.jpg', 3),
(5, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143402/detail_5143402_17479837118731_500.jpg', 4),
(5, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143402/detail_5143402_17479837121739_500.jpg', 5),
(5, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143402/detail_5143402_17479837124579_500.jpg', 6),
(5, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143402/detail_5143402_17479837127547_500.jpg', 7);

-- Emerald-003 - 그물 스트랩 플랫 샌들 (타크트로이메)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(6, 'https://image.msscdn.net/thumbnails/images/goods_img/20240514/4129020/4129020_17156701759638_500.jpg', 1),
(6, 'https://image.msscdn.net/thumbnails/images/prd_img/20240514/4129020/detail_4129020_17156701827874_500.jpg', 2),
(6, 'https://image.msscdn.net/thumbnails/images/prd_img/20240514/4129020/detail_4129020_17156701839705_500.jpg', 3),
(6, 'https://image.msscdn.net/thumbnails/images/prd_img/20240514/4129020/detail_4129020_17156701854097_500.jpg', 4),
(6, 'https://image.msscdn.net/thumbnails/images/prd_img/20240514/4129020/detail_4129020_17156701864218_500.jpg', 5),
(6, 'https://image.msscdn.net/thumbnails/images/prd_img/20240514/4129020/detail_4129020_17156701874449_500.jpg', 6);

-- GO76 리베카 루프 샌들 (비에스큐티바이클래시)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(7, 'https://image.msscdn.net/thumbnails/images/goods_img/20250619/5194897/5194897_17510076994678_500.jpg', 1),
(7, 'https://image.msscdn.net/thumbnails/images/prd_img/20250619/5194897/detail_5194897_17510077060837_500.jpg', 2),
(7, 'https://image.msscdn.net/thumbnails/images/prd_img/20250619/5194897/detail_5194897_17510077108643_500.jpg', 3),
(7, 'https://image.msscdn.net/thumbnails/images/prd_img/20250619/5194897/detail_5194897_17510077148874_500.jpg', 4);

-- [WOMEN] 트래블 플로우 샌들 - 블랙 UFF7103MSHBLK (헌터)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(8, 'https://image.msscdn.net/thumbnails/images/goods_img/20250310/4875489/4875489_17512600561658_500.jpg', 1);

-- NBRJFS480B / SD7401BK (뉴발란스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(9, 'https://image.msscdn.net/thumbnails/images/goods_img/20250617/5189617/5189617_17510108508472_500.jpg', 1),
(9, 'https://image.msscdn.net/thumbnails/images/prd_img/20250617/5189617/detail_5189617_17510108571034_500.jpg', 2),
(9, 'https://image.msscdn.net/thumbnails/images/prd_img/20250617/5189617/detail_5189617_17501376855229_500.jpg', 3),
(9, 'https://image.msscdn.net/thumbnails/images/prd_img/20250617/5189617/detail_5189617_17501376863717_500.jpg', 4),
(9, 'https://image.msscdn.net/thumbnails/images/prd_img/20250617/5189617/detail_5189617_17501376871579_500.jpg', 5),
(9, 'https://image.msscdn.net/thumbnails/images/prd_img/20250617/5189617/detail_5189617_17501376880895_500.jpg', 6),
(9, 'https://image.msscdn.net/thumbnails/images/prd_img/20250617/5189617/detail_5189617_17501376900240_500.jpg', 7),
(9, 'https://image.msscdn.net/thumbnails/images/prd_img/20250617/5189617/detail_5189617_17501376914737_500.jpg', 8);

-- RONDA CS2005 ALL BLACK (츄바스코)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(10, 'https://image.msscdn.net/thumbnails/images/goods_img/20210427/1923386/1923386_5_500.jpg', 1),
(10, 'https://image.msscdn.net/thumbnails/images/prd_img/20210427/1923386/detail_1923386_17192122809143_500.jpg', 2),
(10, 'https://image.msscdn.net/thumbnails/images/prd_img/20210427/1923386/detail_1923386_17192122897777_500.jpg', 3),
(10, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_62677078c97d6.jpg', 4),
(10, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_62676ff04d8e5.jpg', 5),
(10, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_62627364535e9.jpg', 6),
(10, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6099e03ee87d1.jpg', 7),
(10, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6099db7897823.jpg', 8),
(10, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6098d984b9040.jpg', 9),
(10, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6090e0e88d293.jpg', 10);

-- 웨이브뮬 (푸마)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(11, 'https://image.msscdn.net/thumbnails/images/goods_img/20241104/4584275/4584275_17306825087054_500.jpg', 1),
(11, 'https://image.msscdn.net/thumbnails/images/prd_img/20241104/4584275/detail_4584275_17306825117967_500.jpg', 2),
(11, 'https://image.msscdn.net/thumbnails/images/prd_img/20241104/4584275/detail_4584275_17306825131355_500.jpg', 3),
(11, 'https://image.msscdn.net/thumbnails/images/prd_img/20241104/4584275/detail_4584275_17306825144488_500.jpg', 4),
(11, 'https://image.msscdn.net/thumbnails/images/prd_img/20241104/4584275/detail_4584275_17306825161201_500.jpg', 5),
(11, 'https://image.msscdn.net/thumbnails/images/prd_img/20241104/4584275/detail_4584275_17306825177636_500.jpg', 6),
(11, 'https://image.msscdn.net/thumbnails/images/prd_img/20241104/4584275/detail_4584275_17306825190407_500.jpg', 7);

-- PLUSH SANDAL (엄브로)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(12, 'https://image.msscdn.net/thumbnails/images/goods_img/20250321/4929164/4929164_17515024602583_500.jpg', 1),
(12, 'https://image.msscdn.net/thumbnails/images/prd_img/20250321/4929164/detail_4929164_17515024613570_500.jpg', 2),
(12, 'https://image.msscdn.net/thumbnails/images/prd_img/20250321/4929164/detail_4929164_17515024615406_500.jpg', 3),
(12, 'https://image.msscdn.net/thumbnails/images/prd_img/20250321/4929164/detail_4929164_17515024617381_500.jpg', 4),
(12, 'https://image.msscdn.net/thumbnails/images/prd_img/20250321/4929164/detail_4929164_17515024619980_500.jpg', 5),
(12, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/18/48b58d7748cf4dd3ba141b90ab8a7968.jpg', 6);

-- 19B503 black loafer (프리플라)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(13, 'https://image.msscdn.net/thumbnails/images/goods_img/20190909/1147638/1147638_1_500.jpg', 1);

-- 웨버 싸이클론 (슈마커)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(14, 'https://image.msscdn.net/thumbnails/images/goods_img/20240521/4145238/4145238_17162726026596_500.jpg', 1);

-- 여성 하이킹 샌들 제트론 HGC (험토)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(15, 'https://image.msscdn.net/thumbnails/images/goods_img/20240311/3939622/3939622_17473773508069_500.png', 1),
(15, 'https://image.msscdn.net/thumbnails/images/prd_img/20240311/3939622/detail_3939622_17473773879739_500.png', 2),
(15, 'https://image.msscdn.net/thumbnails/images/prd_img/20240311/3939622/detail_3939622_17473773831222_500.png', 3),
(15, 'https://image.msscdn.net/thumbnails/images/prd_img/20240311/3939622/detail_3939622_17473774195786_500.png', 4),
(15, 'https://image.msscdn.net/thumbnails/images/prd_img/20240311/3939622/detail_3939622_17303682778367_500.png', 5),
(15, 'https://image.msscdn.net/thumbnails/images/prd_img/20240311/3939622/detail_3939622_17303610241900_500.png', 6),
(15, 'https://image.msscdn.net/thumbnails/images/prd_img/20240311/3939622/detail_3939622_17303610250340_500.png', 7),
(15, 'https://image.msscdn.net/thumbnails/images/prd_img/20240311/3939622/detail_3939622_17303610258392_500.png', 8),
(15, 'https://image.msscdn.net/thumbnails/images/prd_img/20240311/3939622/detail_3939622_17303610266002_500.png', 9);

-- [클로이] 블랙 스퀘어 샌들 (질바이질스튜어트)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(16, 'https://image.msscdn.net/thumbnails/images/goods_img/20240426/4091326/4091326_17446794688862_500.jpg', 1),
(16, 'https://image.msscdn.net/thumbnails/images/prd_img/20240426/4091326/detail_4091326_17141198106886_500.jpg', 2);

-- 트리플 버클 슬링백 4cm (슈펜)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(17, 'https://image.msscdn.net/thumbnails/images/goods_img/20250526/5146320/5146320_17482413544611_500.jpg', 1),
(17, 'https://image.msscdn.net/thumbnails/images/prd_img/20250526/5146320/detail_5146320_17482413552167_500.jpg', 2),
(17, 'https://image.msscdn.net/thumbnails/images/prd_img/20250526/5146320/detail_5146320_17482413554784_500.jpg', 3),
(17, 'https://image.msscdn.net/thumbnails/images/prd_img/20250526/5146320/detail_5146320_17482413557496_500.jpg', 4),
(17, 'https://image.msscdn.net/thumbnails/images/prd_img/20250526/5146320/detail_5146320_17482413559938_500.jpg', 5);

-- 이지 로프 샌들 블랙 (기호)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(18, 'https://image.msscdn.net/thumbnails/images/goods_img/20250414/5025539/5025539_17457284972375_500.jpg', 1),
(18, 'https://image.msscdn.net/thumbnails/images/prd_img/20250414/5025539/detail_5025539_17457285044967_500.jpg', 2),
(18, 'https://image.msscdn.net/thumbnails/images/prd_img/20250414/5025539/detail_5025539_17457285057162_500.jpg', 3),
(18, 'https://image.msscdn.net/thumbnails/images/prd_img/20250414/5025539/detail_5025539_17457285098164_500.jpg', 4),
(18, 'https://image.msscdn.net/thumbnails/images/prd_img/20250414/5025539/detail_5025539_17457285105887_500.jpg', 5),
(18, 'https://image.msscdn.net/thumbnails/images/prd_img/20250414/5025539/detail_5025539_17457285113936_500.jpg', 6),
(18, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/11/32ba62673e1e47b6b896526adb708136.jpg', 7),
(18, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/11/aae6074fec0b4905b1e28f5288e57db6.jpg', 8),
(18, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/11/4f522daaa9d24ca19178a2caf1982ad2.jpg', 9),
(18, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/11/0e7e07bc158b46389f9e03a21bdebd8a.jpg', 10);

-- 메건 스틸레토 슬링백 [n098]_2color (누스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(19, 'https://image.msscdn.net/thumbnails/images/goods_img/20250327/4952219/4952219_17430539501372_500.jpg', 1),
(19, 'https://image.msscdn.net/thumbnails/images/prd_img/20250327/4952219/detail_4952219_17430498687013_500.jpg', 2),
(19, 'https://image.msscdn.net/thumbnails/images/prd_img/20250327/4952219/detail_4952219_17430498699404_500.jpg', 3),
(19, 'https://image.msscdn.net/thumbnails/images/prd_img/20250327/4952219/detail_4952219_17430498712540_500.jpg', 4),
(19, 'https://image.msscdn.net/thumbnails/images/prd_img/20250327/4952219/detail_4952219_17430498724971_500.jpg', 5),
(19, 'https://image.msscdn.net/thumbnails/images/prd_img/20250327/4952219/detail_4952219_17430498733991_500.jpg', 6),
(19, 'https://image.msscdn.net/thumbnails/images/prd_img/20250327/4952219/detail_4952219_17430498744313_500.jpg', 7),
(19, 'https://image.msscdn.net/thumbnails/images/prd_img/20250327/4952219/detail_4952219_17430539620873_500.jpg', 8);

-- 그루피 샌들 - 블랙:골드 / 722309WBED11080 (발렌시아가)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(20, 'https://image.msscdn.net/thumbnails/images/goods_img/20230330/3192376/3192376_16861916518887_500.jpg', 1),
(20, 'https://image.msscdn.net/thumbnails/images/prd_img/20230330/3192376/detail_3192376_16861916555027_500.jpg', 2),
(20, 'https://image.msscdn.net/thumbnails/images/prd_img/20230330/3192376/detail_3192376_16861916567278_500.jpg', 3),
(20, 'https://image.msscdn.net/thumbnails/images/prd_img/20230330/3192376/detail_3192376_16861916578037_500.jpg', 4),
(20, 'https://image.msscdn.net/thumbnails/images/prd_img/20230330/3192376/detail_3192376_16861916592416_500.jpg', 5),
(20, 'https://image.msscdn.net/thumbnails/images/prd_img/20230330/3192376/detail_3192376_16861916608178_500.jpg', 6),
(20, 'https://image.msscdn.net/thumbnails/images/prd_img/20230330/3192376/detail_3192376_16861916627370_500.jpg', 7),
(20, 'https://image.msscdn.net/thumbnails/images/prd_img/20230330/3192376/detail_3192376_16861916646207_500.jpg', 8);

-- 인텐스 남성 스퀘어 페니 로퍼 드레스 3cm LCMD35I513 (엘칸토)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(21, 'https://image.msscdn.net/thumbnails/images/goods_img/20250211/4774305/4774305_17399228067273_500.jpg', 1),
(21, 'https://image.msscdn.net/thumbnails/images/prd_img/20250211/4774305/detail_4774305_17392318126635_500.jpg', 2),
(21, 'https://image.msscdn.net/thumbnails/images/prd_img/20250211/4774305/detail_4774305_17392318140644_500.jpg', 3),
(21, 'https://image.msscdn.net/thumbnails/images/prd_img/20250211/4774305/detail_4774305_17392318154092_500.jpg', 4),
(21, 'https://image.msscdn.net/thumbnails/images/prd_img/20250211/4774305/detail_4774305_17392318165786_500.jpg', 5),
(21, 'https://image.msscdn.net/thumbnails/images/prd_img/20250211/4774305/detail_4774305_17392318176153_500.jpg', 6),
(21, 'https://image.msscdn.net/thumbnails/images/prd_img/20250211/4774305/detail_4774305_17392318188383_500.jpg', 7);

-- 하이페리엄 슬라이드 - 베이지 / 100251039 (리복)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(22, 'https://image.msscdn.net/thumbnails/images/goods_img/20250423/5055516/5055516_17453751844870_500.jpg', 1),
(22, 'https://image.msscdn.net/thumbnails/images/prd_img/20250423/5055516/detail_5055516_17453752213744_500.jpg', 2),
(22, 'https://image.msscdn.net/thumbnails/images/prd_img/20250423/5055516/detail_5055516_17453752238218_500.jpg', 3),
(22, 'https://image.msscdn.net/thumbnails/images/prd_img/20250423/5055516/detail_5055516_17453752222676_500.jpg', 4),
(22, 'https://image.msscdn.net/thumbnails/images/prd_img/20250423/5055516/detail_5055516_17453752231481_500.jpg', 5),
(22, 'https://image.msscdn.net/thumbnails/images/prd_img/20250423/5055516/detail_5055516_17453752246516_500.jpg', 6),
(22, 'https://image.msscdn.net/thumbnails/images/prd_img/20250423/5055516/detail_5055516_17453752255744_500.jpg', 7),
(22, 'https://image.msscdn.net/thumbnails/snap/images/2025/05/27/9f0e529370494421af26769795041752.jpg', 8),
(22, 'https://image.msscdn.net/thumbnails/snap/images/2025/05/22/c0ab61a816d84d55886992e92191eb49.jpg', 9);

--  (호카)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(23, 'https://image.msscdn.net/thumbnails/images/goods_img/20240130/3833073/3833073_17078745794650_500.jpg', 1),
(23, 'https://image.msscdn.net/thumbnails/images/prd_img/20240130/3833073/detail_3833073_17078745922057_500.jpg', 2),
(23, 'https://image.msscdn.net/thumbnails/images/prd_img/20240130/3833073/detail_3833073_17078745899612_500.jpg', 3),
(23, 'https://image.msscdn.net/thumbnails/images/prd_img/20240130/3833073/detail_3833073_17078745871946_500.jpg', 4),
(23, 'https://image.msscdn.net/thumbnails/images/prd_img/20240130/3833073/detail_3833073_17078745879924_500.jpg', 5),
(23, 'https://image.msscdn.net/thumbnails/images/prd_img/20240130/3833073/detail_3833073_17078745890664_500.jpg', 6),
(23, 'https://image.msscdn.net/thumbnails/images/prd_img/20240130/3833073/detail_3833073_17078745864409_500.jpg', 7),
(23, 'https://image.msscdn.net/thumbnails/images/prd_img/20240130/3833073/detail_3833073_17078745910607_500.jpg', 8);

-- 보스 쿼드 블랙 하이드로 샌들 / 26725001 (닥터마틴)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(24, 'https://image.msscdn.net/thumbnails/images/goods_img/20210223/1810765/1810765_1_500.jpg', 1),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_1_500.jpg', 2),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_2_500.jpg', 3),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_4_500.jpg', 4),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_7_500.jpg', 5),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_11_500.jpg', 6),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_16_500.jpg', 7),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_22_500.jpg', 8),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_29_500.jpg', 9),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_30_500.jpg', 10),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_31_500.jpg', 11),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_33_500.jpg', 12),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_36_500.jpg', 13),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_40_500.jpg', 14),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_45_500.jpg', 15),
(24, 'https://image.msscdn.net/thumbnails/images/prd_img/20210223/1810765/detail_1810765_51_500.jpg', 16);

-- 쥬아나 피셔맨 샌들 (사뿐)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(25, 'https://image.msscdn.net/thumbnails/images/goods_img/20230510/3286833/3286833_17169489635065_500.jpg', 1),
(25, 'https://image.msscdn.net/thumbnails/images/prd_img/20230510/3286833/detail_3286833_17169489645024_500.jpg', 2),
(25, 'https://image.msscdn.net/thumbnails/images/prd_img/20230510/3286833/detail_3286833_17169489647521_500.jpg', 3),
(25, 'https://image.msscdn.net/thumbnails/images/prd_img/20230510/3286833/detail_3286833_17169489649320_500.jpg', 4),
(25, 'https://image.msscdn.net/thumbnails/images/prd_img/20230510/3286833/detail_3286833_17169489651748_500.jpg', 5),
(25, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/30/fca5b82ae42a487b86d36b5b93e1c5ee.jpg', 6),
(25, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/30/f221098a7dc5472992d0246495a9f99c.jpg', 7);

-- 1YD XSLIDE (식스핏)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(26, 'https://image.msscdn.net/thumbnails/images/goods_img/20240523/4150123/4150123_17170446982899_500.jpg', 1),
(26, 'https://image.msscdn.net/thumbnails/images/prd_img/20240523/4150123/detail_4150123_17177282199127_500.jpg', 2),
(26, 'https://image.msscdn.net/thumbnails/images/prd_img/20240523/4150123/detail_4150123_17177282207191_500.jpg', 3),
(26, 'https://image.msscdn.net/thumbnails/images/prd_img/20240523/4150123/detail_4150123_17177282216856_500.jpg', 4),
(26, 'https://image.msscdn.net/thumbnails/images/prd_img/20240523/4150123/detail_4150123_17177282225700_500.jpg', 5);

-- [슈퍼세일]센트리 올드스쿨 WC - (반스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(27, 'https://image.msscdn.net/thumbnails/images/goods_img/20221005/2839678/2839678_1_500.jpg', 1),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_1_500.jpg', 2),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_2_500.jpg', 3),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_4_500.jpg', 4),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_7_500.jpg', 5),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_11_500.jpg', 6),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_16_500.jpg', 7),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_17_500.jpg', 8),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_18_500.jpg', 9),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_20_500.jpg', 10),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_23_500.jpg', 11),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_27_500.jpg', 12),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_32_500.jpg', 13),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_38_500.jpg', 14),
(27, 'https://image.msscdn.net/thumbnails/images/prd_img/20221005/2839678/detail_2839678_45_500.jpg', 15);

-- 리본 메쉬 샌들 (슈펜)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(28, 'https://image.msscdn.net/thumbnails/images/goods_img/20250513/5106883/5106883_17471127170642_500.jpg', 1),
(28, 'https://image.msscdn.net/thumbnails/images/prd_img/20250513/5106883/detail_5106883_17471127181083_500.jpg', 2),
(28, 'https://image.msscdn.net/thumbnails/images/prd_img/20250513/5106883/detail_5106883_17471127183549_500.jpg', 3),
(28, 'https://image.msscdn.net/thumbnails/images/prd_img/20250513/5106883/detail_5106883_17471127185704_500.jpg', 4),
(28, 'https://image.msscdn.net/thumbnails/images/prd_img/20250513/5106883/detail_5106883_17471127187990_500.jpg', 5);

-- [리퍼브] KM223 핸드메이드 카우레더 아치 스트랩 플랫폼 샌들 천연가죽 ver. l 5Colors (코모레비뮤지엄)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(29, 'https://image.msscdn.net/thumbnails/images/goods_img/20250617/5191123/5191123_17501480976946_500.jpg', 1),
(29, 'https://image.msscdn.net/thumbnails/images/prd_img/20250617/5191123/detail_5191123_17501481036869_500.jpg', 2),
(29, 'https://image.msscdn.net/thumbnails/images/prd_img/20250617/5191123/detail_5191123_17501481103062_500.jpg', 3);

-- GO77 릭센 파이어컷 샌들 (비에스큐티바이클래시)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(30, 'https://image.msscdn.net/thumbnails/images/goods_img/20250619/5194896/5194896_17510076605169_500.jpg', 1),
(30, 'https://image.msscdn.net/thumbnails/images/prd_img/20250619/5194896/detail_5194896_17510076664538_500.jpg', 2),
(30, 'https://image.msscdn.net/thumbnails/images/prd_img/20250619/5194896/detail_5194896_17510076704645_500.jpg', 3),
(30, 'https://image.msscdn.net/thumbnails/images/prd_img/20250619/5194896/detail_5194896_17510076754384_500.jpg', 4);

-- 청키샌들 NY (엠엘비)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(31, 'https://image.msscdn.net/thumbnails/images/goods_img/20230331/3197910/3197910_17201543122829_500.jpg', 1),
(31, 'https://image.msscdn.net/thumbnails/images/prd_img/20230331/3197910/detail_3197910_16802398632831_500.jpg', 2),
(31, 'https://image.msscdn.net/thumbnails/images/prd_img/20230331/3197910/detail_3197910_16802398640750_500.jpg', 3),
(31, 'https://image.msscdn.net/thumbnails/images/prd_img/20230331/3197910/detail_3197910_16802398651970_500.jpg', 4),
(31, 'https://image.msscdn.net/thumbnails/images/prd_img/20230331/3197910/detail_3197910_16802398663810_500.jpg', 5),
(31, 'https://image.msscdn.net/thumbnails/images/prd_img/20230331/3197910/detail_3197910_16802398676810_500.jpg', 6);

-- [리퍼브] KM069 핸드메이드 카우레더 청키 솔 피셔맨 샌들 6cm I 4Colors (코모레비뮤지엄)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(32, 'https://image.msscdn.net/thumbnails/images/goods_img/20250618/5192357/5192357_17502165659696_500.jpg', 1),
(32, 'https://image.msscdn.net/thumbnails/images/prd_img/20250618/5192357/detail_5192357_17502165708444_500.jpg', 2),
(32, 'https://image.msscdn.net/thumbnails/images/prd_img/20250618/5192357/detail_5192357_17502165755803_500.jpg', 3),
(32, 'https://image.msscdn.net/thumbnails/images/prd_img/20250618/5192357/detail_5192357_17502165798474_500.jpg', 4),
(32, 'https://image.msscdn.net/thumbnails/images/prd_img/20250618/5192357/detail_5192357_17502165844994_500.jpg', 5);

-- 엑시드 스트랩 레더 샌들 [BLACK] (드로우핏)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(33, 'https://image.msscdn.net/thumbnails/images/goods_img/20210429/1928038/1928038_17448601585397_500.jpg', 1),
(33, 'https://image.msscdn.net/thumbnails/images/prd_img/20210429/1928038/detail_1928038_17448601672572_500.jpg', 2),
(33, 'https://image.msscdn.net/thumbnails/images/prd_img/20210429/1928038/detail_1928038_17448601681232_500.jpg', 3),
(33, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60dab69b3bf36.jpg', 4);

-- 인텐스 여성 트렌디 리본 플랫 샌들 2cm LCWW61I526 (엘칸토)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(34, 'https://image.msscdn.net/thumbnails/images/goods_img/20250325/4937924/4937924_17428588597073_500.jpg', 1),
(34, 'https://image.msscdn.net/thumbnails/images/prd_img/20250325/4937924/detail_4937924_17428588627357_500.jpg', 2),
(34, 'https://image.msscdn.net/thumbnails/images/prd_img/20250325/4937924/detail_4937924_17428588640517_500.jpg', 3),
(34, 'https://image.msscdn.net/thumbnails/images/prd_img/20250325/4937924/detail_4937924_17428588654890_500.jpg', 4),
(34, 'https://image.msscdn.net/thumbnails/images/prd_img/20250325/4937924/detail_4937924_17428588665287_500.jpg', 5),
(34, 'https://image.msscdn.net/thumbnails/images/prd_img/20250325/4937924/detail_4937924_17428588675681_500.jpg', 6),
(34, 'https://image.msscdn.net/thumbnails/images/prd_img/20250325/4937924/detail_4937924_17428588688885_500.jpg', 7);

-- 젤-카야노 14 - 화이트:그래파이트 그레이 / 1203A537-110 (아식스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(35, 'https://image.msscdn.net/thumbnails/images/goods_img/20250630/5213548/5213548_17512631003915_500.jpg', 1),
(35, 'https://image.msscdn.net/thumbnails/images/prd_img/20250630/5213548/detail_5213548_17512631081958_500.jpg', 2),
(35, 'https://image.msscdn.net/thumbnails/images/prd_img/20250630/5213548/detail_5213548_17512631090320_500.jpg', 3),
(35, 'https://image.msscdn.net/thumbnails/images/prd_img/20250630/5213548/detail_5213548_17512631099820_500.jpg', 4),
(35, 'https://image.msscdn.net/thumbnails/images/prd_img/20250630/5213548/detail_5213548_17512631108678_500.jpg', 5),
(35, 'https://image.msscdn.net/thumbnails/images/prd_img/20250630/5213548/detail_5213548_17512631118337_500.jpg', 6),
(35, 'https://image.msscdn.net/thumbnails/images/prd_img/20250630/5213548/detail_5213548_17512631127549_500.jpg', 7),
(35, 'https://image.msscdn.net/thumbnails/images/prd_img/20250630/5213548/detail_5213548_17512631137505_500.jpg', 8);

-- 노트 스트링 플랫폼 샌들 (누스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(36, 'https://image.msscdn.net/thumbnails/images/goods_img/20230523/3318485/3318485_16889735961381_500.jpg', 1),
(36, 'https://image.msscdn.net/thumbnails/images/prd_img/20230523/3318485/detail_3318485_16889736077664_500.jpg', 2),
(36, 'https://image.msscdn.net/thumbnails/images/prd_img/20230523/3318485/detail_3318485_16880265792702_500.jpg', 3),
(36, 'https://image.msscdn.net/thumbnails/images/prd_img/20230523/3318485/detail_3318485_16880266115525_500.jpg', 4),
(36, 'https://image.msscdn.net/thumbnails/images/prd_img/20230523/3318485/detail_3318485_16880266046522_500.jpg', 5),
(36, 'https://image.msscdn.net/thumbnails/images/prd_img/20230523/3318485/detail_3318485_16880265884500_500.jpg', 6),
(36, 'https://image.msscdn.net/thumbnails/images/prd_img/20230523/3318485/detail_3318485_16880265731869_500.jpg', 7),
(36, 'https://image.msscdn.net/thumbnails/images/prd_img/20230523/3318485/detail_3318485_16880266240264_500.jpg', 8),
(36, 'https://image.msscdn.net/thumbnails/images/prd_img/20230523/3318485/detail_3318485_16848282128030_500.jpg', 9),
(36, 'https://image.msscdn.net/thumbnails/images/prd_img/20230523/3318485/detail_3318485_16848282201231_500.jpg', 10),
(36, 'https://image.msscdn.net/thumbnails/images/prd_img/20230523/3318485/detail_3318485_16848282263584_500.jpg', 11);

-- BUCKLE EYELET SLIPPERS IN BLACK (마뗑킴)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(37, 'https://image.msscdn.net/thumbnails/images/goods_img/20250526/5145781/5145781_17482351019795_500.jpg', 1);

-- [WOMEN] 시티 레블 네오 레더 버클 샌들 - 블랙 UFD3109LEABLK (헌터)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(38, 'https://image.msscdn.net/thumbnails/images/goods_img/20250401/4973538/4973538_17440736223938_500.jpg', 1),
(38, 'https://image.msscdn.net/thumbnails/images/prd_img/20250401/4973538/detail_4973538_17440736278352_500.jpg', 2),
(38, 'https://image.msscdn.net/thumbnails/images/prd_img/20250401/4973538/detail_4973538_17440736286780_500.jpg', 3),
(38, 'https://image.msscdn.net/thumbnails/images/prd_img/20250401/4973538/detail_4973538_17440736293043_500.jpg', 4);

-- 루아 투웨이 컴포트 샌들 (착한구두)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(39, 'https://image.msscdn.net/thumbnails/images/goods_img/20250417/5038724/5038724_17448676710425_500.jpg', 1),
(39, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038724/detail_5038724_17496085348205_500.jpg', 2),
(39, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038724/detail_5038724_17496085425709_500.jpg', 3),
(39, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038724/detail_5038724_17496085464572_500.jpg', 4),
(39, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038724/detail_5038724_17496085502554_500.jpg', 5);

-- 아딜렛 22 - 다크그레이 / HP6522 (아디다스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(40, 'https://image.msscdn.net/thumbnails/images/goods_img/20230426/3262218/3262218_16830140039641_500.jpg', 1),
(40, 'https://image.msscdn.net/thumbnails/images/prd_img/20230426/3262218/detail_3262218_16830140087448_500.jpg', 2),
(40, 'https://image.msscdn.net/thumbnails/images/prd_img/20230426/3262218/detail_3262218_16830140095926_500.jpg', 3),
(40, 'https://image.msscdn.net/thumbnails/images/prd_img/20230426/3262218/detail_3262218_16830140105875_500.jpg', 4),
(40, 'https://image.msscdn.net/thumbnails/images/prd_img/20230426/3262218/detail_3262218_16830140117731_500.jpg', 5),
(40, 'https://image.msscdn.net/thumbnails/images/prd_img/20230426/3262218/detail_3262218_16830140133338_500.jpg', 6),
(40, 'https://image.msscdn.net/thumbnails/images/prd_img/20230426/3262218/detail_3262218_16830140149994_500.jpg', 7),
(40, 'https://image.msscdn.net/thumbnails/images/prd_img/20230426/3262218/detail_3262218_16830140168075_500.jpg', 8),
(40, 'https://image.msscdn.net/thumbnails/images/prd_img/20230426/3262218/detail_3262218_16830140187303_500.jpg', 9);

-- 루즈핏 하프부츠 HA2419 (알뜨알레)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(41, 'https://image.msscdn.net/thumbnails/images/goods_img/20240919/4445577/4445577_17267343747007_500.jpg', 1),
(41, 'https://image.msscdn.net/thumbnails/images/prd_img/20240919/4445577/detail_4445577_17267350274629_500.jpg', 2),
(41, 'https://image.msscdn.net/thumbnails/images/prd_img/20240919/4445577/detail_4445577_17267350310838_500.jpg', 3);

-- 아린스 에나멜 슬링백힐 (사뿐)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(42, 'https://image.msscdn.net/thumbnails/images/goods_img/20240207/3852562/3852562_17072705457736_500.jpg', 1),
(42, 'https://image.msscdn.net/thumbnails/images/prd_img/20240207/3852562/detail_3852562_17072705489453_500.jpg', 2),
(42, 'https://image.msscdn.net/thumbnails/images/prd_img/20240207/3852562/detail_3852562_17072705502690_500.jpg', 3),
(42, 'https://image.msscdn.net/thumbnails/images/prd_img/20240207/3852562/detail_3852562_17072705508833_500.jpg', 4),
(42, 'https://image.msscdn.net/thumbnails/images/prd_img/20240207/3852562/detail_3852562_17072705517527_500.jpg', 5);

-- 에이미 샌들 (케즈)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(43, 'https://image.msscdn.net/thumbnails/images/goods_img/20250523/5143404/5143404_17479837517151_500.jpg', 1),
(43, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143404/detail_5143404_17479837524946_500.jpg', 2),
(43, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143404/detail_5143404_17479837529027_500.jpg', 3),
(43, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143404/detail_5143404_17479837531652_500.jpg', 4),
(43, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143404/detail_5143404_17479837535308_500.jpg', 5),
(43, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143404/detail_5143404_17479837538131_500.jpg', 6),
(43, 'https://image.msscdn.net/thumbnails/images/prd_img/20250523/5143404/detail_5143404_17479837541739_500.jpg', 7);

-- 신시아 메리제인 클로그 2color (모그어스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(44, 'https://image.msscdn.net/thumbnails/images/goods_img/20250508/5093449/5093449_17466725932317_500.jpg', 1),
(44, 'https://image.msscdn.net/thumbnails/images/prd_img/20250508/5093449/detail_5093449_17466726103003_500.jpg', 2),
(44, 'https://image.msscdn.net/thumbnails/images/prd_img/20250508/5093449/detail_5093449_17466726110818_500.jpg', 3),
(44, 'https://image.msscdn.net/thumbnails/images/prd_img/20250508/5093449/detail_5093449_17466726119593_500.jpg', 4),
(44, 'https://image.msscdn.net/thumbnails/images/prd_img/20250508/5093449/detail_5093449_17466726080590_500.jpg', 5),
(44, 'https://image.msscdn.net/thumbnails/images/prd_img/20250508/5093449/detail_5093449_17466726088896_500.jpg', 6),
(44, 'https://image.msscdn.net/thumbnails/images/prd_img/20250508/5093449/detail_5093449_17466726095432_500.jpg', 7);

-- 츄비01 스트랩 샌들 412524006 (솔트앤초콜릿)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(45, 'https://image.msscdn.net/thumbnails/images/goods_img/20250520/5129582/5129582_17477302350292_500.jpg', 1),
(45, 'https://image.msscdn.net/thumbnails/images/prd_img/20250520/5129582/detail_5129582_17477303422593_500.jpg', 2),
(45, 'https://image.msscdn.net/thumbnails/images/prd_img/20250520/5129582/detail_5129582_17477303409883_500.jpg', 3),
(45, 'https://image.msscdn.net/thumbnails/images/prd_img/20250520/5129582/detail_5129582_17477302484771_500.jpg', 4),
(45, 'https://image.msscdn.net/thumbnails/images/prd_img/20250520/5129582/detail_5129582_17477071651960_500.jpg', 5),
(45, 'https://image.msscdn.net/thumbnails/images/prd_img/20250520/5129582/detail_5129582_17477071655974_500.jpg', 6),
(45, 'https://image.msscdn.net/thumbnails/images/prd_img/20250520/5129582/detail_5129582_17477303055801_500.jpg', 7);

-- 트리뷰트 플랫 뮬 - 블랙 / 571952BDA001000 (생로랑)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(46, 'https://image.msscdn.net/thumbnails/images/goods_img/20250224/4820676/4820676_17413143577402_500.jpg', 1),
(46, 'https://image.msscdn.net/thumbnails/images/prd_img/20250224/4820676/detail_4820676_17413143627942_500.jpg', 2),
(46, 'https://image.msscdn.net/thumbnails/images/prd_img/20250224/4820676/detail_4820676_17413143635843_500.jpg', 3),
(46, 'https://image.msscdn.net/thumbnails/images/prd_img/20250224/4820676/detail_4820676_17413143642765_500.jpg', 4),
(46, 'https://image.msscdn.net/thumbnails/images/prd_img/20250224/4820676/detail_4820676_17413143650888_500.jpg', 5),
(46, 'https://image.msscdn.net/thumbnails/images/prd_img/20250224/4820676/detail_4820676_17413143658389_500.jpg', 6),
(46, 'https://image.msscdn.net/thumbnails/images/prd_img/20250224/4820676/detail_4820676_17413143666377_500.jpg', 7),
(46, 'https://image.msscdn.net/thumbnails/images/prd_img/20250224/4820676/detail_4820676_17413143674139_500.jpg', 8);

-- 마쯔 여성 고프코어 캐주얼 스니커즈 4cm LCWC24M413 (엘칸토)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(47, 'https://image.msscdn.net/thumbnails/images/goods_img/20240115/3794771/3794771_17052934924402_500.jpg', 1),
(47, 'https://image.msscdn.net/thumbnails/images/prd_img/20240115/3794771/detail_3794771_17052935012527_500.jpg', 2),
(47, 'https://image.msscdn.net/thumbnails/images/prd_img/20240115/3794771/detail_3794771_17052935079164_500.jpg', 3),
(47, 'https://image.msscdn.net/thumbnails/images/prd_img/20240115/3794771/detail_3794771_17052935146363_500.jpg', 4),
(47, 'https://image.msscdn.net/thumbnails/images/prd_img/20240115/3794771/detail_3794771_17052935189431_500.jpg', 5),
(47, 'https://image.msscdn.net/thumbnails/images/prd_img/20240115/3794771/detail_3794771_17052935224248_500.jpg', 6),
(47, 'https://image.msscdn.net/thumbnails/images/prd_img/20240115/3794771/detail_3794771_17052935299359_500.jpg', 7);

-- FL4724 르블랑 바이커 버클 스트랩 부츠 (플로리다 스튜디오)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(48, 'https://image.msscdn.net/thumbnails/images/goods_img/20241010/4503796/4503796_17297326339904_500.jpg', 1),
(48, 'https://image.msscdn.net/thumbnails/images/prd_img/20241010/4503796/detail_4503796_17297326400578_500.jpg', 2),
(48, 'https://image.msscdn.net/thumbnails/images/prd_img/20241010/4503796/detail_4503796_17297326462881_500.jpg', 3),
(48, 'https://image.msscdn.net/thumbnails/images/prd_img/20241010/4503796/detail_4503796_17297326500992_500.jpg', 4);

-- Loel 버클 스트랩 웨스턴 2TYPE 부츠 (레이지앤)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(49, 'https://image.msscdn.net/thumbnails/images/goods_img/20240806/4299698/4299698_17252456003310_500.jpg', 1),
(49, 'https://image.msscdn.net/thumbnails/images/prd_img/20240806/4299698/detail_4299698_17252456480424_500.jpg', 2),
(49, 'https://image.msscdn.net/thumbnails/images/prd_img/20240806/4299698/detail_4299698_17252456493233_500.jpg', 3),
(49, 'https://image.msscdn.net/thumbnails/images/prd_img/20240806/4299698/detail_4299698_17252456557071_500.jpg', 4),
(49, 'https://image.msscdn.net/thumbnails/images/prd_img/20240806/4299698/detail_4299698_17252456544938_500.jpg', 5),
(49, 'https://image.msscdn.net/thumbnails/images/prd_img/20240806/4299698/detail_4299698_17252456569436_500.jpg', 6),
(49, 'https://image.msscdn.net/thumbnails/images/prd_img/20240806/4299698/detail_4299698_17252456505760_500.jpg', 7),
(49, 'https://image.msscdn.net/thumbnails/images/prd_img/20240806/4299698/detail_4299698_17252456518603_500.jpg', 8),
(49, 'https://image.msscdn.net/thumbnails/images/prd_img/20240806/4299698/detail_4299698_17252456531752_500.jpg', 9);

-- NBRJFS440B / 브리즈 / SD2202BK2 (뉴발란스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(50, 'https://image.msscdn.net/thumbnails/images/goods_img/20250311/4878032/4878032_17418321777947_500.jpg', 1),
(50, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4878032/detail_4878032_17418321905263_500.jpg', 2),
(50, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4878032/detail_4878032_17418321914604_500.jpg', 3),
(50, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4878032/detail_4878032_17418321924093_500.jpg', 4),
(50, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4878032/detail_4878032_17418321933531_500.jpg', 5),
(50, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4878032/detail_4878032_17418321942421_500.jpg', 6),
(50, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4878032/detail_4878032_17418321961126_500.jpg', 7),
(50, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4878032/detail_4878032_17418321976250_500.jpg', 8);

-- ljh9001 mules _ 3colors (프리플라)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(51, 'https://image.msscdn.net/thumbnails/images/goods_img/20210517/1953154/1953154_16812302725178_500.jpg', 1);

-- CHUBASCO ALMERIA CA2203 TAN (츄바스코)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(52, 'https://image.msscdn.net/thumbnails/images/goods_img/20230502/3271676/3271676_17139249954464_500.jpg', 1),
(52, 'https://image.msscdn.net/thumbnails/images/prd_img/20230502/3271676/detail_3271676_17216145042089_500.jpg', 2),
(52, 'https://image.msscdn.net/thumbnails/images/prd_img/20230502/3271676/detail_3271676_17216157079257_500.jpg', 3),
(52, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64c2127fe76f5.jpg', 4);

-- 마쯔 여성 글래디에이터 청키 샌들 3cm LCWW93M526 (엘칸토)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(53, 'https://image.msscdn.net/thumbnails/images/goods_img/20250429/5074911/5074911_17458875878051_500.jpg', 1),
(53, 'https://image.msscdn.net/thumbnails/images/prd_img/20250429/5074911/detail_5074911_17458875916403_500.jpg', 2),
(53, 'https://image.msscdn.net/thumbnails/images/prd_img/20250429/5074911/detail_5074911_17458875931597_500.jpg', 3),
(53, 'https://image.msscdn.net/thumbnails/images/prd_img/20250429/5074911/detail_5074911_17458875947151_500.jpg', 4),
(53, 'https://image.msscdn.net/thumbnails/images/prd_img/20250429/5074911/detail_5074911_17458875961155_500.jpg', 5),
(53, 'https://image.msscdn.net/thumbnails/images/prd_img/20250429/5074911/detail_5074911_17458875972774_500.jpg', 6),
(53, 'https://image.msscdn.net/thumbnails/images/prd_img/20250429/5074911/detail_5074911_17458875987679_500.jpg', 7);

-- [서지수 PICK]GLAIR SLINGBACK SANDALS - 6 colors (락피쉬웨더웨어)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(54, 'https://image.msscdn.net/thumbnails/images/goods_img/20250519/5125579/5125579_17515886182924_500.jpg', 1),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17502313328271_500.jpg', 2),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17502313311183_500.jpg', 3),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17502313257652_500.jpg', 4),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17502313276867_500.jpg', 5),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17502313286060_500.jpg', 6),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17502313266097_500.jpg', 7),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17502313294989_500.jpg', 8),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278258196_500.jpg', 9),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278265665_500.jpg', 10),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278272521_500.jpg', 11),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278279865_500.jpg', 12),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278286781_500.jpg', 13),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278293147_500.jpg', 14),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278300917_500.jpg', 15),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278306442_500.jpg', 16),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278311662_500.jpg', 17),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278317896_500.jpg', 18),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278324490_500.jpg', 19),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278332331_500.jpg', 20),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278338504_500.jpg', 21),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278345774_500.jpg', 22),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278351685_500.jpg', 23),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278358028_500.jpg', 24),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278363949_500.jpg', 25),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278246947_500.jpg', 26),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278252464_500.jpg', 27),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278486724_500.jpg', 28),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278491562_500.jpg', 29),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278497355_500.jpg', 30),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278503350_500.jpg', 31),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278509434_500.jpg', 32),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278370926_500.jpg', 33),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278376004_500.jpg', 34),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278382040_500.jpg', 35),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278387215_500.jpg', 36),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278393192_500.jpg', 37),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278400549_500.jpg', 38),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278405790_500.jpg', 39),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278411432_500.jpg', 40),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278416419_500.jpg', 41),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278422354_500.jpg', 42),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278427498_500.jpg', 43),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278432867_500.jpg', 44),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278438521_500.jpg', 45),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278443663_500.jpg', 46),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278449961_500.jpg', 47),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278456235_500.jpg', 48),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278462179_500.jpg', 49),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278468204_500.jpg', 50),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278473822_500.jpg', 51),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278480111_500.jpg', 52),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278515818_500.jpg', 53),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278521128_500.jpg', 54),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278526730_500.jpg', 55),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278531260_500.jpg', 56),
(54, 'https://image.msscdn.net/thumbnails/images/prd_img/20250519/5125579/detail_5125579_17478278538286_500.jpg', 57);

-- Sahara 사하라 네트형 빈티지 피셔맨_3color 2cm (마크모크)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(55, 'https://image.msscdn.net/thumbnails/images/goods_img/20250408/4999183/4999183_17445919628916_500.jpg', 1);

-- 코듀라 비트닉 - 락 블랙 / FY2949 (리복)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(56, 'https://image.msscdn.net/thumbnails/images/goods_img/20230407/3215382/3215382_16808434025177_500.jpg', 1),
(56, 'https://image.msscdn.net/thumbnails/images/prd_img/20230407/3215382/detail_3215382_16808434231706_500.jpg', 2),
(56, 'https://image.msscdn.net/thumbnails/images/prd_img/20230407/3215382/detail_3215382_16808434212240_500.jpg', 3),
(56, 'https://image.msscdn.net/thumbnails/images/prd_img/20230407/3215382/detail_3215382_16808434193894_500.jpg', 4),
(56, 'https://image.msscdn.net/thumbnails/images/prd_img/20230407/3215382/detail_3215382_16808434256133_500.jpg', 5),
(56, 'https://image.msscdn.net/thumbnails/images/prd_img/20230407/3215382/detail_3215382_16808434152409_500.jpg', 6),
(56, 'https://image.msscdn.net/thumbnails/images/prd_img/20230407/3215382/detail_3215382_16808434176387_500.jpg', 7),
(56, 'https://image.msscdn.net/thumbnails/images/prd_img/20230407/3215382/detail_3215382_16808434164045_500.jpg', 8),
(56, 'https://image.msscdn.net/thumbnails/images/prd_img/20230407/3215382/detail_3215382_16808434300277_500.jpg', 9),
(56, 'https://image.msscdn.net/thumbnails/images/prd_img/20230407/3215382/detail_3215382_16808434276146_500.jpg', 10),
(56, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64880849b9f41.jpg', 11),
(56, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_645c86a612122.jpg', 12),
(56, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_645b46b1be415.jpg', 13),
(56, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_645b45ee0f4ec.jpg', 14),
(56, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_644770a287969.jpg', 15);

-- 레이넬 클래식 페니 로퍼 (착한구두)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(57, 'https://image.msscdn.net/thumbnails/images/goods_img/20250404/4988986/4988986_17525654944688_500.jpg', 1),
(57, 'https://image.msscdn.net/thumbnails/images/prd_img/20250404/4988986/detail_4988986_17525655005981_500.jpg', 2),
(57, 'https://image.msscdn.net/thumbnails/images/prd_img/20250404/4988986/detail_4988986_17525655014732_500.jpg', 3),
(57, 'https://image.msscdn.net/thumbnails/images/prd_img/20250404/4988986/detail_4988986_17525655023479_500.jpg', 4),
(57, 'https://image.msscdn.net/thumbnails/images/prd_img/20250404/4988986/detail_4988986_17525655031852_500.jpg', 5);

-- 인텐스 여성 버클 슬링백 2cm LCWO92I513 (엘칸토)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(58, 'https://image.msscdn.net/thumbnails/images/goods_img/20250110/4709327/4709327_17364718722589_500.jpg', 1),
(58, 'https://image.msscdn.net/thumbnails/images/prd_img/20250110/4709327/detail_4709327_17364662888788_500.jpg', 2),
(58, 'https://image.msscdn.net/thumbnails/images/prd_img/20250110/4709327/detail_4709327_17364662902950_500.jpg', 3),
(58, 'https://image.msscdn.net/thumbnails/images/prd_img/20250110/4709327/detail_4709327_17364662917174_500.jpg', 4),
(58, 'https://image.msscdn.net/thumbnails/images/prd_img/20250110/4709327/detail_4709327_17364662929262_500.jpg', 5),
(58, 'https://image.msscdn.net/thumbnails/images/prd_img/20250110/4709327/detail_4709327_17364662940148_500.jpg', 6),
(58, 'https://image.msscdn.net/thumbnails/images/prd_img/20250110/4709327/detail_4709327_17364662954143_500.jpg', 7);

-- 5.5cm 벨크로 피셔맨 플랫폼 샌들 윌라 (커스텀에이드 우먼)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(59, 'https://image.msscdn.net/thumbnails/images/goods_img/20240530/4166303/4166303_17494533353728_500.jpg', 1),
(59, 'https://image.msscdn.net/thumbnails/images/prd_img/20240530/4166303/detail_4166303_17494533499151_500.jpg', 2),
(59, 'https://image.msscdn.net/thumbnails/images/prd_img/20240530/4166303/detail_4166303_17494533527368_500.jpg', 3),
(59, 'https://image.msscdn.net/thumbnails/images/prd_img/20240530/4166303/detail_4166303_17494533508729_500.jpg', 4),
(59, 'https://image.msscdn.net/thumbnails/images/prd_img/20240530/4166303/detail_4166303_17494533517340_500.jpg', 5),
(59, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_669da901d22ec.jpg', 6),
(59, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_668cae9f07ff7.jpg', 7),
(59, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6685e7d390144.jpg', 8),
(59, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_667a190690e7e.jpg', 9);

-- 오션 선 피쉬 샌들 (잔카)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(60, 'https://image.msscdn.net/thumbnails/images/goods_img/20250513/5106879/5106879_17471126550556_500.jpg', 1);

-- Creek - 멀티 버클 스트랩 샌들 (타크트로이메)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(61, 'https://image.msscdn.net/thumbnails/images/goods_img/20220704/2645639/2645639_17476235629665_500.jpg', 1),
(61, 'https://image.msscdn.net/thumbnails/images/prd_img/20220704/2645639/detail_2645639_17476235702684_500.jpg', 2),
(61, 'https://image.msscdn.net/thumbnails/images/prd_img/20220704/2645639/detail_2645639_16889703666451_500.jpg', 3),
(61, 'https://image.msscdn.net/thumbnails/images/prd_img/20220704/2645639/detail_2645639_17476235712309_500.jpg', 4),
(61, 'https://image.msscdn.net/thumbnails/images/prd_img/20220704/2645639/detail_2645639_16889703682156_500.jpg', 5),
(61, 'https://image.msscdn.net/thumbnails/images/prd_img/20220704/2645639/detail_2645639_16889703751117_500.jpg', 6),
(61, 'https://image.msscdn.net/thumbnails/images/prd_img/20220704/2645639/detail_2645639_17476235721926_500.jpg', 7);

-- 남성 프리미엄 베이직 부츠 위트브라운_TB1180942311 (팀버랜드)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(62, 'https://image.msscdn.net/thumbnails/images/goods_img/20240809/4313969/4313969_17231729917740_500.jpg', 1);

-- 남성 시톤 베이 슬라이드 탄_TB0A2422F131 (팀버랜드)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(63, 'https://image.msscdn.net/thumbnails/images/goods_img/20250429/5077402/5077402_17459086748520_500.jpg', 1);

-- [리버] 블랙 플랫폼 로퍼 (질바이질스튜어트)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(64, 'https://image.msscdn.net/thumbnails/images/goods_img/20230116/3027317/3027317_16806691735937_500.jpg', 1);

-- LEEDS (엄브로)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(65, 'https://image.msscdn.net/thumbnails/images/goods_img/20250321/4929128/4929128_17524518314703_500.jpg', 1),
(65, 'https://image.msscdn.net/thumbnails/images/prd_img/20250321/4929128/detail_4929128_17524518326001_500.jpg', 2),
(65, 'https://image.msscdn.net/thumbnails/images/prd_img/20250321/4929128/detail_4929128_17524518327856_500.jpg', 3),
(65, 'https://image.msscdn.net/thumbnails/images/prd_img/20250321/4929128/detail_4929128_17524518329614_500.jpg', 4),
(65, 'https://image.msscdn.net/thumbnails/images/prd_img/20250321/4929128/detail_4929128_17524518331666_500.jpg', 5),
(65, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/09/97f2ff22fc6c4c13b0f5611d529278fb.jpg', 6),
(65, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/04/229985ad901b49b99a42180078dc4fe4.jpg', 7);

-- 니들워크 슬라이드 R25M700 (로맨틱무브)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(66, 'https://image.msscdn.net/thumbnails/images/goods_img/20250417/5038328/5038328_17461480708189_500.jpg', 1),
(66, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038328/detail_5038328_17452253523137_500.jpg', 2),
(66, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038328/detail_5038328_17461480522830_500.jpg', 3),
(66, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038328/detail_5038328_17452253531030_500.jpg', 4),
(66, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038328/detail_5038328_17452253538591_500.jpg', 5),
(66, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038328/detail_5038328_17452253546060_500.jpg', 6),
(66, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038328/detail_5038328_17452253556480_500.jpg', 7),
(66, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038328/detail_5038328_17452253563839_500.jpg', 8),
(66, 'https://image.msscdn.net/thumbnails/images/prd_img/20250417/5038328/detail_5038328_17452253572711_500.jpg', 9),
(66, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/30/d99a9655ecb947b09dc787f228d73b47.jpg', 10),
(66, 'https://image.msscdn.net/thumbnails/snap/images/2025/05/27/953e0414edbb44d986955033770e4c65.jpg', 11),
(66, 'https://image.msscdn.net/thumbnails/snap/images/2025/05/19/d3fc79d6265e4d4581e4015ea227140f.jpg', 12),
(66, 'https://image.msscdn.net/thumbnails/snap/images/2025/05/07/e9ed93ce92c44dfc8c1cd520f40b89a3.jpg', 13);

-- 텐더폼 하이브_Black (다이나핏)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(67, 'https://image.msscdn.net/thumbnails/images/goods_img/20220503/2538107/2538107_1_500.jpg', 1);

-- SECOND.A 더블라인 베이직 레더샌들 2NDS010B [BLACK] (피렌체 아뜨리에)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(68, 'https://image.msscdn.net/thumbnails/images/goods_img/20210421/1912404/1912404_17171558467914_500.jpg', 1),
(68, 'https://image.msscdn.net/thumbnails/images/prd_img/20210421/1912404/detail_1912404_17169469601622_500.jpg', 2),
(68, 'https://image.msscdn.net/thumbnails/images/prd_img/20210421/1912404/detail_1912404_17169469627511_500.jpg', 3),
(68, 'https://image.msscdn.net/thumbnails/images/prd_img/20210421/1912404/detail_1912404_17169469652810_500.jpg', 4),
(68, 'https://image.msscdn.net/thumbnails/images/prd_img/20210421/1912404/detail_1912404_17169469684607_500.jpg', 5),
(68, 'https://image.msscdn.net/thumbnails/images/prd_img/20210421/1912404/detail_1912404_17169469710132_500.jpg', 6),
(68, 'https://image.msscdn.net/thumbnails/images/prd_img/20210421/1912404/detail_1912404_17169469747501_500.jpg', 7),
(68, 'https://image.msscdn.net/thumbnails/images/prd_img/20210421/1912404/detail_1912404_17169469773725_500.jpg', 8),
(68, 'https://image.msscdn.net/thumbnails/images/prd_img/20210421/1912404/detail_1912404_17169469803270_500.jpg', 9),
(68, 'https://image.msscdn.net/thumbnails/images/prd_img/20210421/1912404/detail_1912404_17169469828596_500.jpg', 10),
(68, 'https://image.msscdn.net/thumbnails/images/prd_img/20210421/1912404/detail_1912404_17169469855758_500.jpg', 11),
(68, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60fe21b49e775.jpg', 12);

-- 클라우드 메리제인 (케즈)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(69, 'https://image.msscdn.net/thumbnails/images/goods_img/20240118/3806869/3806869_17114398569826_500.jpg', 1),
(69, 'https://image.msscdn.net/thumbnails/images/prd_img/20240118/3806869/detail_3806869_17078887675367_500.jpg', 2),
(69, 'https://image.msscdn.net/thumbnails/images/prd_img/20240118/3806869/detail_3806869_17078887680876_500.jpg', 3),
(69, 'https://image.msscdn.net/thumbnails/images/prd_img/20240118/3806869/detail_3806869_17078887685145_500.jpg', 4),
(69, 'https://image.msscdn.net/thumbnails/images/prd_img/20240118/3806869/detail_3806869_17078887689223_500.jpg', 5),
(69, 'https://image.msscdn.net/thumbnails/images/prd_img/20240118/3806869/detail_3806869_17078887693506_500.jpg', 6),
(69, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_666a8e98a295c.jpg', 7),
(69, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_665d3f1a1da35.jpg', 8),
(69, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_664af401f3cbf.jpg', 9);

-- 젤-1090 W - 화이트:미드나이트 / 1022A215-100 (아식스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(70, 'https://image.msscdn.net/thumbnails/images/goods_img/20200207/1296778/1296778_2_500.jpg', 1),
(70, 'https://image.msscdn.net/thumbnails/images/prd_img/20200207/1296778/detail_1296778_6_500.jpg', 2),
(70, 'https://image.msscdn.net/thumbnails/images/prd_img/20200207/1296778/detail_1296778_7_500.jpg', 3),
(70, 'https://image.msscdn.net/thumbnails/images/prd_img/20200207/1296778/detail_1296778_8_500.jpg', 4),
(70, 'https://image.msscdn.net/thumbnails/images/prd_img/20200207/1296778/detail_1296778_9_500.jpg', 5),
(70, 'https://image.msscdn.net/thumbnails/images/prd_img/20200207/1296778/detail_1296778_10_500.jpg', 6),
(70, 'https://img.youtube.com/vi/R8CekxHDsN0/0.jpg', 7);

-- 92BNN ELAN BUCKLE-BROWN (예루살렘 샌들)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(71, 'https://image.msscdn.net/thumbnails/images/goods_img/20250314/4898245/4898245_17453702096468_500.jpg', 1);

-- 에이스 - 블랙 / 31530001 (닥터마틴)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(72, 'https://image.msscdn.net/thumbnails/images/goods_img/20240319/3966132/3966132_17119597642348_500.jpg', 1),
(72, 'https://image.msscdn.net/thumbnails/images/prd_img/20240319/3966132/detail_3966132_17119597718622_500.jpg', 2),
(72, 'https://image.msscdn.net/thumbnails/images/prd_img/20240319/3966132/detail_3966132_17119597725686_500.jpg', 3),
(72, 'https://image.msscdn.net/thumbnails/images/prd_img/20240319/3966132/detail_3966132_17119597733559_500.jpg', 4),
(72, 'https://image.msscdn.net/thumbnails/images/prd_img/20240319/3966132/detail_3966132_17119597742471_500.jpg', 5),
(72, 'https://image.msscdn.net/thumbnails/images/prd_img/20240319/3966132/detail_3966132_17119597751199_500.jpg', 6),
(72, 'https://image.msscdn.net/thumbnails/images/prd_img/20240319/3966132/detail_3966132_17119597759768_500.jpg', 7),
(72, 'https://image.msscdn.net/thumbnails/images/prd_img/20240319/3966132/detail_3966132_17119597767901_500.jpg', 8),
(72, 'https://image.msscdn.net/thumbnails/images/prd_img/20240319/3966132/detail_3966132_17119597775438_500.jpg', 9);

-- WTRD4607 올오버 위브 샌들 블랙 [230~290MM] (위더로드)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(73, 'https://image.msscdn.net/thumbnails/images/goods_img/20230503/3273723/3273723_17198208392104_500.jpg', 1),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738343718_500.jpg', 2),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738359392_500.jpg', 3),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738374661_500.jpg', 4),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738394079_500.jpg', 5),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738414322_500.jpg', 6),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738436100_500.jpg', 7),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738455335_500.jpg', 8),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738475660_500.jpg', 9),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738517859_500.jpg', 10),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738545723_500.jpg', 11),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738573453_500.jpg', 12),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738608075_500.jpg', 13),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738644257_500.jpg', 14),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738673987_500.jpg', 15),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738713794_500.jpg', 16),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738757013_500.jpg', 17),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738794551_500.jpg', 18),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738827714_500.jpg', 19),
(73, 'https://image.msscdn.net/thumbnails/images/prd_img/20230503/3273723/detail_3273723_16843738866439_500.jpg', 20),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_66737f4abf245.jpg', 21),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6503c077013ba.jpg', 22),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64f7d89ce168a.jpg', 23),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64f7daabe7abd.jpg', 24),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64ae0affd382d.jpg', 25),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64ae031e5c393.jpg', 26),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6492b34e79068.jpg', 27),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6487d333834f3.jpg', 28),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6486b5e9146e8.jpg', 29),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6486b80434f3c.jpg', 30),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_648024c0322d4.jpg', 31),
(73, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_647ff0a153efc.jpg', 32);

-- 시부이 뮬 - 아이보리 / 394883-03 (푸마)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(74, 'https://image.msscdn.net/thumbnails/images/goods_img/20230411/3222874/3222874_16820518256946_500.jpg', 1),
(74, 'https://image.msscdn.net/thumbnails/images/prd_img/20230411/3222874/detail_3222874_16820518359221_500.jpg', 2),
(74, 'https://image.msscdn.net/thumbnails/images/prd_img/20230411/3222874/detail_3222874_16820518371415_500.jpg', 3),
(74, 'https://image.msscdn.net/thumbnails/images/prd_img/20230411/3222874/detail_3222874_16820518383591_500.jpg', 4),
(74, 'https://image.msscdn.net/thumbnails/images/prd_img/20230411/3222874/detail_3222874_16820518398730_500.jpg', 5),
(74, 'https://image.msscdn.net/thumbnails/images/prd_img/20230411/3222874/detail_3222874_16820518412555_500.jpg', 6),
(74, 'https://image.msscdn.net/thumbnails/images/prd_img/20230411/3222874/detail_3222874_16820518431092_500.jpg', 7),
(74, 'https://image.msscdn.net/thumbnails/images/prd_img/20230411/3222874/detail_3222874_16820518451165_500.jpg', 8),
(74, 'https://image.msscdn.net/thumbnails/images/prd_img/20230411/3222874/detail_3222874_16820518474035_500.jpg', 9);

-- 에센셜 슬라이드 블랙 A12174C (컨버스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(75, 'https://image.msscdn.net/thumbnails/images/goods_img/20250311/4879732/4879732_17416760013561_500.jpg', 1),
(75, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4879732/detail_4879732_17416760220329_500.jpg', 2),
(75, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4879732/detail_4879732_17416760210265_500.jpg', 3),
(75, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4879732/detail_4879732_17416763190367_500.jpg', 4),
(75, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4879732/detail_4879732_17416760118710_500.jpg', 5),
(75, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4879732/detail_4879732_17416760127138_500.jpg', 6),
(75, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4879732/detail_4879732_17416760141038_500.jpg', 7),
(75, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4879732/detail_4879732_17416760168144_500.jpg', 8),
(75, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4879732/detail_4879732_17416760190962_500.jpg', 9),
(75, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4879732/detail_4879732_17416760200869_500.jpg', 10),
(75, 'https://image.msscdn.net/thumbnails/images/prd_img/20250311/4879732/detail_4879732_17416760107765_500.jpg', 11);

-- GO45 로크린 메쉬 스포츠 샌들 (비에스큐티바이클래시)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(76, 'https://image.msscdn.net/thumbnails/images/goods_img/20250425/5067626/5067626_17458239380079_500.jpg', 1),
(76, 'https://image.msscdn.net/thumbnails/images/prd_img/20250425/5067626/detail_5067626_17458239463859_500.jpg', 2),
(76, 'https://image.msscdn.net/thumbnails/images/prd_img/20250425/5067626/detail_5067626_17458239495373_500.jpg', 3),
(76, 'https://image.msscdn.net/thumbnails/images/prd_img/20250425/5067626/detail_5067626_17458239534447_500.jpg', 4);

-- NBPFFF750L / W480SK5 (뉴발란스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(77, 'https://image.msscdn.net/thumbnails/images/goods_img/20240226/3901126/3901126_17479644701162_500.jpg', 1),
(77, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3901126/detail_3901126_17479644840333_500.jpg', 2),
(77, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3901126/detail_3901126_17479644849754_500.jpg', 3),
(77, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3901126/detail_3901126_17479644858311_500.jpg', 4),
(77, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3901126/detail_3901126_17479644867641_500.jpg', 5),
(77, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3901126/detail_3901126_17479644875451_500.jpg', 6),
(77, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3901126/detail_3901126_17479644885702_500.jpg', 7),
(77, 'https://image.msscdn.net/thumbnails/images/prd_img/20240226/3901126/detail_3901126_17479644896493_500.jpg', 8);

-- 마쯔 여성 토캡 메리제인 캐주얼 스니커즈 4.5cm LCWC90M513 (엘칸토)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(78, 'https://image.msscdn.net/thumbnails/images/goods_img/20250116/4723567/4723567_17388077451839_500.jpg', 1),
(78, 'https://image.msscdn.net/thumbnails/images/prd_img/20250116/4723567/detail_4723567_17388077772457_500.jpg', 2),
(78, 'https://image.msscdn.net/thumbnails/images/prd_img/20250116/4723567/detail_4723567_17388077923190_500.jpg', 3),
(78, 'https://image.msscdn.net/thumbnails/images/prd_img/20250116/4723567/detail_4723567_17369825825002_500.jpg', 4),
(78, 'https://image.msscdn.net/thumbnails/images/prd_img/20250116/4723567/detail_4723567_17369825772698_500.jpg', 5),
(78, 'https://image.msscdn.net/thumbnails/images/prd_img/20250116/4723567/detail_4723567_17369825803553_500.jpg', 6),
(78, 'https://image.msscdn.net/thumbnails/images/prd_img/20250116/4723567/detail_4723567_17369825837217_500.jpg', 7);

-- CLARO (엄브로)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(79, 'https://image.msscdn.net/thumbnails/images/goods_img/20240205/3845822/3845822_17310353621495_500.jpg', 1),
(79, 'https://image.msscdn.net/thumbnails/images/prd_img/20240205/3845822/detail_3845822_17310353628600_500.jpg', 2),
(79, 'https://image.msscdn.net/thumbnails/images/prd_img/20240205/3845822/detail_3845822_17071069032058_500.jpg', 3),
(79, 'https://image.msscdn.net/thumbnails/images/prd_img/20240205/3845822/detail_3845822_17071069037701_500.jpg', 4),
(79, 'https://image.msscdn.net/thumbnails/images/prd_img/20240205/3845822/detail_3845822_17071069042584_500.jpg', 5);

-- 푸마 투리노 II SD - 그레이:오크 / 397453-03 (푸마)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(80, 'https://image.msscdn.net/thumbnails/images/goods_img/20241008/4495201/4495201_17288886049288_500.jpg', 1),
(80, 'https://image.msscdn.net/thumbnails/images/prd_img/20241008/4495201/detail_4495201_17288886115192_500.jpg', 2),
(80, 'https://image.msscdn.net/thumbnails/images/prd_img/20241008/4495201/detail_4495201_17288886122601_500.jpg', 3),
(80, 'https://image.msscdn.net/thumbnails/images/prd_img/20241008/4495201/detail_4495201_17288886129437_500.jpg', 4),
(80, 'https://image.msscdn.net/thumbnails/images/prd_img/20241008/4495201/detail_4495201_17288886137855_500.jpg', 5),
(80, 'https://image.msscdn.net/thumbnails/images/prd_img/20241008/4495201/detail_4495201_17288886145589_500.jpg', 6);

-- 레이첼 라운드 스트랩 샌들 (사뿐)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(81, 'https://image.msscdn.net/thumbnails/images/goods_img/20240429/4095283/4095283_17143793829256_500.jpg', 1),
(81, 'https://image.msscdn.net/thumbnails/images/prd_img/20240429/4095283/detail_4095283_17143793838472_500.jpg', 2),
(81, 'https://image.msscdn.net/thumbnails/images/prd_img/20240429/4095283/detail_4095283_17143793840211_500.jpg', 3),
(81, 'https://image.msscdn.net/thumbnails/images/prd_img/20240429/4095283/detail_4095283_17143793842585_500.jpg', 4),
(81, 'https://image.msscdn.net/thumbnails/images/prd_img/20240429/4095283/detail_4095283_17143793844649_500.jpg', 5),
(81, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_668617663ea46.jpg', 6);

-- [가드너] 스퀘어메리제인 슬링백 2.5 HPWWVD201S (슈펜)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(82, 'https://image.msscdn.net/thumbnails/images/goods_img/20230314/3144895/3144895_16787545241725_500.jpg', 1),
(82, 'https://image.msscdn.net/thumbnails/images/prd_img/20230314/3144895/detail_3144895_16787545420334_500.jpg', 2),
(82, 'https://image.msscdn.net/thumbnails/images/prd_img/20230314/3144895/detail_3144895_16787545635443_500.jpg', 3),
(82, 'https://image.msscdn.net/thumbnails/images/prd_img/20230314/3144895/detail_3144895_16787545424584_500.jpg', 4),
(82, 'https://image.msscdn.net/thumbnails/images/prd_img/20230314/3144895/detail_3144895_16787545428609_500.jpg', 5),
(82, 'https://image.msscdn.net/thumbnails/images/prd_img/20230314/3144895/detail_3144895_16787545433465_500.jpg', 6);

-- [워터 리지스턴트] 콤피 스트링 샌들 412424003 (솔트앤초콜릿)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(83, 'https://image.msscdn.net/thumbnails/images/goods_img/20240517/4138411/4138411_17502317227285_500.jpg', 1),
(83, 'https://image.msscdn.net/thumbnails/images/prd_img/20240517/4138411/detail_4138411_17502313775901_500.jpg', 2),
(83, 'https://image.msscdn.net/thumbnails/images/prd_img/20240517/4138411/detail_4138411_17167910113055_500.jpg', 3),
(83, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_66826172cec0c.jpg', 4),
(83, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_66826145550bb.jpg', 5),
(83, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_667520723411e.jpg', 6),
(83, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6670db5512502.jpg', 7),
(83, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6670dafbbec94.jpg', 8);

-- 셔링 스트랩 플랫폼 샌들 (세스띠)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(84, 'https://image.msscdn.net/thumbnails/images/goods_img/20230522/3314501/3314501_16847329100006_500.jpg', 1),
(84, 'https://image.msscdn.net/thumbnails/images/prd_img/20230522/3314501/detail_3314501_16847329450548_500.jpg', 2),
(84, 'https://image.msscdn.net/thumbnails/images/prd_img/20230522/3314501/detail_3314501_16847329524686_500.jpg', 3),
(84, 'https://image.msscdn.net/thumbnails/images/prd_img/20230522/3314501/detail_3314501_16847329434783_500.jpg', 4),
(84, 'https://image.msscdn.net/thumbnails/images/prd_img/20230522/3314501/detail_3314501_16847329408786_500.jpg', 5),
(84, 'https://image.msscdn.net/thumbnails/images/prd_img/20230522/3314501/detail_3314501_16847329417835_500.jpg', 6),
(84, 'https://image.msscdn.net/thumbnails/images/prd_img/20230522/3314501/detail_3314501_16847329473059_500.jpg', 7),
(84, 'https://image.msscdn.net/thumbnails/images/prd_img/20230522/3314501/detail_3314501_16847329499770_500.jpg', 8),
(84, 'https://image.msscdn.net/thumbnails/images/prd_img/20230522/3314501/detail_3314501_16847329550525_500.jpg', 9);

-- CHUBASCO NUBE MEN SLIDE CB2301 ALL BLACK (츄바스코)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(85, 'https://image.msscdn.net/thumbnails/images/goods_img/20230607/3348606/3348606_17139253178788_500.jpg', 1),
(85, 'https://image.msscdn.net/thumbnails/images/prd_img/20230607/3348606/detail_3348606_17186070018538_500.jpg', 2),
(85, 'https://image.msscdn.net/thumbnails/images/prd_img/20230607/3348606/detail_3348606_17186070049388_500.jpg', 3),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64d31d0ccc0a7.jpg', 4),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64d2eb57ceb4b.jpg', 5),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64ae41ebb5352.jpg', 6),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64abb8aaa0c83.jpg', 7),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64aba6371be75.jpg', 8),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64aba37366f3d.jpg', 9),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64a654823f853.jpg', 10),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64a6518112e37.jpg', 11),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64a503a368ffb.jpg', 12),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64a2752476889.jpg', 13),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_64a2736434d5e.jpg', 14),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_649d37ee1b04d.jpg', 15),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_649d29055f2fd.jpg', 16),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_649d29f3379a0.jpg', 17),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_649d2d1e8669b.jpg', 18),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_649be52a07c53.jpg', 19),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_649be0c5292dd.jpg', 20),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_649bddde0a884.jpg', 21),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_649bda1b70148.jpg', 22),
(85, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_649bd7c183f9e.jpg', 23);

-- NBRJFS430I / Cove / SD4205IV2 (뉴발란스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(86, 'https://image.msscdn.net/thumbnails/images/goods_img/20240411/4042912/4042912_17449388851157_500.jpg', 1),
(86, 'https://image.msscdn.net/thumbnails/images/prd_img/20240411/4042912/detail_4042912_17449389677518_500.jpg', 2),
(86, 'https://image.msscdn.net/thumbnails/images/prd_img/20240411/4042912/detail_4042912_17449389688479_500.jpg', 3),
(86, 'https://image.msscdn.net/thumbnails/images/prd_img/20240411/4042912/detail_4042912_17449389700803_500.jpg', 4),
(86, 'https://image.msscdn.net/thumbnails/images/prd_img/20240411/4042912/detail_4042912_17449389708449_500.jpg', 5),
(86, 'https://image.msscdn.net/thumbnails/images/prd_img/20240411/4042912/detail_4042912_17449389718222_500.jpg', 6),
(86, 'https://image.msscdn.net/thumbnails/images/prd_img/20240411/4042912/detail_4042912_17449389734040_500.jpg', 7),
(86, 'https://image.msscdn.net/thumbnails/images/prd_img/20240411/4042912/detail_4042912_17449389752183_500.jpg', 8),
(86, 'https://image.msscdn.net/thumbnails/images/prd_img/20240411/4042912/detail_4042912_17449389764563_500.jpg', 9);

-- 스티치 포인트 샌들 EBAL157318 (엘리자베스 스튜어트)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(87, 'https://image.msscdn.net/thumbnails/images/goods_img/20250325/4939875/4939875_17471845225986_500.jpg', 1),
(87, 'https://image.msscdn.net/thumbnails/images/prd_img/20250325/4939875/detail_4939875_17443321826127_500.jpg', 2),
(87, 'https://image.msscdn.net/thumbnails/images/prd_img/20250325/4939875/detail_4939875_17443321750543_500.jpg', 3),
(87, 'https://image.msscdn.net/thumbnails/images/prd_img/20250325/4939875/detail_4939875_17443321958938_500.jpg', 4);

-- 라운드 토 스티치 라이딩 롱부츠 412348003 (솔트앤초콜릿)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(88, 'https://image.msscdn.net/thumbnails/images/goods_img/20230830/3513077/3513077_16933678909470_500.jpg', 1),
(88, 'https://image.msscdn.net/thumbnails/images/prd_img/20230830/3513077/detail_3513077_16933678945897_500.jpg', 2),
(88, 'https://image.msscdn.net/thumbnails/images/prd_img/20230830/3513077/detail_3513077_16933678951956_500.jpg', 3),
(88, 'https://image.msscdn.net/thumbnails/images/prd_img/20230830/3513077/detail_3513077_16933678958731_500.jpg', 4),
(88, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_65f292c92790c.jpg', 5),
(88, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_65f29245a772f.jpg', 6),
(88, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_65f291f20f12a.jpg', 7),
(88, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_65f29296059f1.jpg', 8),
(88, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_65f28f0687820.jpg', 9);

-- 에어 맥스 코코 W - 블랙:메탈릭 실버 / IH6313-010 (나이키)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(89, 'https://image.msscdn.net/thumbnails/images/goods_img/20250610/5173985/5173985_17502272129920_500.jpg', 1),
(89, 'https://image.msscdn.net/thumbnails/images/prd_img/20250610/5173985/detail_5173985_17502272192542_500.jpg', 2),
(89, 'https://image.msscdn.net/thumbnails/images/prd_img/20250610/5173985/detail_5173985_17502272200906_500.jpg', 3),
(89, 'https://image.msscdn.net/thumbnails/images/prd_img/20250610/5173985/detail_5173985_17502272210582_500.jpg', 4),
(89, 'https://image.msscdn.net/thumbnails/images/prd_img/20250610/5173985/detail_5173985_17502272220455_500.jpg', 5),
(89, 'https://image.msscdn.net/thumbnails/images/prd_img/20250610/5173985/detail_5173985_17502272229184_500.jpg', 6),
(89, 'https://image.msscdn.net/thumbnails/images/prd_img/20250610/5173985/detail_5173985_17502272239428_500.jpg', 7),
(89, 'https://image.msscdn.net/thumbnails/images/prd_img/20250610/5173985/detail_5173985_17502272250328_500.jpg', 8),
(89, 'https://image.msscdn.net/thumbnails/images/prd_img/20250610/5173985/detail_5173985_17502272262813_500.jpg', 9);

-- 노마 피셔맨 샌들 [n5251]_3color (누스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(90, 'https://image.msscdn.net/thumbnails/images/goods_img/20240513/4124709/4124709_17185961472758_500.jpg', 1),
(90, 'https://image.msscdn.net/thumbnails/images/prd_img/20240513/4124709/detail_4124709_17185961737126_500.jpg', 2),
(90, 'https://image.msscdn.net/thumbnails/images/prd_img/20240513/4124709/detail_4124709_17155733487268_500.jpg', 3),
(90, 'https://image.msscdn.net/thumbnails/images/prd_img/20240513/4124709/detail_4124709_17162579059422_500.jpg', 4),
(90, 'https://image.msscdn.net/thumbnails/images/prd_img/20240513/4124709/detail_4124709_17155733517571_500.jpg', 5),
(90, 'https://image.msscdn.net/thumbnails/images/prd_img/20240513/4124709/detail_4124709_17155733529575_500.jpg', 6);

-- MS6394 블레스 더블 스트랩 샌들 블랙 [250~280mm] (파스코로젠)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(91, 'https://image.msscdn.net/thumbnails/images/goods_img/20200429/1428666/1428666_20_500.jpg', 1),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_8_500.jpg', 2),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_9_500.jpg', 3),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_10_500.jpg', 4),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_11_500.jpg', 5),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_12_500.jpg', 6),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_13_500.jpg', 7),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_14_500.jpg', 8),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_15_500.jpg', 9),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_16_500.jpg', 10),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_17_500.jpg', 11),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_18_500.jpg', 12),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_19_500.jpg', 13),
(91, 'https://image.msscdn.net/thumbnails/images/prd_img/20200429/1428666/detail_1428666_20_500.jpg', 14),
(91, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/11/9c3fc5ae8e3648208ff21b3d7e3d0aeb.jpg', 15),
(91, 'https://image.msscdn.net/thumbnails/snap/images/2025/05/30/b47eb7cc85284b97897cf71714ac149c.jpg', 16);

-- 문비치 슬리퍼 R24M800 (로맨틱무브)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(92, 'https://image.msscdn.net/thumbnails/images/goods_img/20240522/4147721/4147721_17473781091350_500.jpg', 1),
(92, 'https://image.msscdn.net/thumbnails/images/prd_img/20240522/4147721/detail_4147721_17473781031892_500.jpg', 2),
(92, 'https://image.msscdn.net/thumbnails/images/prd_img/20240522/4147721/detail_4147721_17472105380012_500.jpg', 3),
(92, 'https://image.msscdn.net/thumbnails/images/prd_img/20240522/4147721/detail_4147721_17472105388174_500.jpg', 4),
(92, 'https://image.msscdn.net/thumbnails/images/prd_img/20240522/4147721/detail_4147721_17472105402802_500.jpg', 5),
(92, 'https://image.msscdn.net/thumbnails/images/prd_img/20240522/4147721/detail_4147721_17472105395504_500.jpg', 6),
(92, 'https://image.msscdn.net/thumbnails/images/prd_img/20240522/4147721/detail_4147721_17472105411187_500.jpg', 7),
(92, 'https://image.msscdn.net/thumbnails/images/prd_img/20240522/4147721/detail_4147721_17472105420121_500.jpg', 8),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/23/c394a9e4a96d49df83f5566b887bfcc3.jpg', 9),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/23/f7f9ed80c3a4411fb4ed32518013e17c.jpg', 10),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/17/8a8c91f393f84bf2a4f7fea533a9622e.jpg', 11),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/17/4dbcbe1b6612479c8e6aaf995a94e887.jpg', 12),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/17/d95ffa9ce5a1497494c6fbca470c9861.jpg', 13),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/17/854fc0b9addd48f08da000665cae5bc8.jpg', 14),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/17/a5c9e5c250644a8d9e65bcd994dd4d66.jpg', 15),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/16/592aee415bdc4d41a2d343a22c15c500.jpg', 16),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/16/2cf5f97b48424d9786caaf82ec885884.jpg', 17),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/16/49f406b7288a4b97a9b12c56ebf8d5b0.jpg', 18),
(92, 'https://image.msscdn.net/thumbnails/snap/images/2025/06/09/53cd48372cda45cf881c3892d1db098a.jpg', 19),
(92, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_667d11e1e55e4.jpg', 20),
(92, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_667a50b6c29ca.jpg', 21),
(92, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_66739ee921e09.jpg', 22),
(92, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6672405a59272.jpg', 23),
(92, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_666faa6e32502.jpg', 24);

-- NS93R44J 화이트라벨 슈퍼 킥스 2 EX_IVORY (노스페이스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(93, 'https://image.msscdn.net/thumbnails/images/goods_img/20250304/4852386/4852386_17410607336635_500.jpg', 1),
(93, 'https://image.msscdn.net/thumbnails/images/prd_img/20250304/4852386/detail_4852386_17410607359476_500.jpg', 2),
(93, 'https://image.msscdn.net/thumbnails/images/prd_img/20250304/4852386/detail_4852386_17410607371913_500.jpg', 3),
(93, 'https://image.msscdn.net/thumbnails/images/prd_img/20250304/4852386/detail_4852386_17410607383547_500.jpg', 4);

-- 플립플랍 패브릭 쪼리 레더 슬리퍼 SFT-402 (슈피트)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(94, 'https://image.msscdn.net/thumbnails/images/goods_img/20240618/4205300/4205300_17187200600323_500.jpg', 1),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187202820107_500.jpg', 2),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187202882400_500.jpg', 3),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187202907619_500.jpg', 4),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187202932650_500.jpg', 5),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187202960030_500.jpg', 6),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187203009351_500.jpg', 7),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187203079871_500.jpg', 8),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187203045460_500.jpg', 9),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187203205104_500.jpg', 10),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187203248330_500.jpg', 11),
(94, 'https://image.msscdn.net/thumbnails/images/prd_img/20240618/4205300/detail_4205300_17187203282977_500.jpg', 12);

-- GRAMPI (엄브로)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(95, 'https://image.msscdn.net/thumbnails/images/goods_img/20240401/4008830/4008830_17501213171276_500.jpg', 1),
(95, 'https://image.msscdn.net/thumbnails/images/prd_img/20240401/4008830/detail_4008830_17126249841035_500.jpg', 2),
(95, 'https://image.msscdn.net/thumbnails/images/prd_img/20240401/4008830/detail_4008830_17119535864017_500.jpg', 3),
(95, 'https://image.msscdn.net/thumbnails/images/prd_img/20240401/4008830/detail_4008830_17485940064520_500.jpg', 4),
(95, 'https://image.msscdn.net/thumbnails/images/prd_img/20240401/4008830/detail_4008830_17485940066296_500.jpg', 5),
(95, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_667a61dd048cb.jpg', 6);

-- GEL-1130 - 버치:다크타우페 / 1201A995-200 (아식스)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(96, 'https://image.msscdn.net/thumbnails/images/goods_img/20250410/5010681/5010681_17442684862852_500.jpg', 1),
(96, 'https://image.msscdn.net/thumbnails/images/prd_img/20250410/5010681/detail_5010681_17442684910322_500.jpg', 2),
(96, 'https://image.msscdn.net/thumbnails/images/prd_img/20250410/5010681/detail_5010681_17442684968582_500.jpg', 3),
(96, 'https://image.msscdn.net/thumbnails/images/prd_img/20250410/5010681/detail_5010681_17442684979313_500.jpg', 4),
(96, 'https://image.msscdn.net/thumbnails/images/prd_img/20250410/5010681/detail_5010681_17442684947517_500.jpg', 5),
(96, 'https://image.msscdn.net/thumbnails/images/prd_img/20250410/5010681/detail_5010681_17442684993193_500.jpg', 6),
(96, 'https://image.msscdn.net/thumbnails/images/prd_img/20250410/5010681/detail_5010681_17442684958033_500.jpg', 7);

-- 여성 린덴 우즈 6인치 레이스업 부츠 위트브라운_TB1A161G2311 (팀버랜드)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(97, 'https://image.msscdn.net/thumbnails/images/goods_img/20250522/5137524/5137524_17478804561614_500.jpg', 1);

-- 리네아 스니커즈 412430001 (솔트앤초콜릿)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(98, 'https://image.msscdn.net/thumbnails/images/goods_img/20241008/4497489/4497489_17283662901255_500.jpg', 1),
(98, 'https://image.msscdn.net/thumbnails/images/prd_img/20241008/4497489/detail_4497489_17283662965932_500.jpg', 2),
(98, 'https://image.msscdn.net/thumbnails/images/prd_img/20241008/4497489/detail_4497489_17283662996932_500.jpg', 3),
(98, 'https://image.msscdn.net/thumbnails/images/prd_img/20241008/4497489/detail_4497489_17310461927621_500.jpg', 4);

-- 산토리니 샌들 레더 블랙 552 (야세)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(99, 'https://image.msscdn.net/thumbnails/images/goods_img/20200424/1419869/1419869_17218696015955_500.jpg', 1),
(99, 'https://image.msscdn.net/thumbnails/images/prd_img/20200424/1419869/detail_1419869_1_500.jpg', 2),
(99, 'https://image.msscdn.net/thumbnails/images/prd_img/20200424/1419869/detail_1419869_2_500.jpg', 3),
(99, 'https://image.msscdn.net/thumbnails/images/prd_img/20200424/1419869/detail_1419869_3_500.jpg', 4),
(99, 'https://image.msscdn.net/thumbnails/images/prd_img/20200424/1419869/detail_1419869_4_500.jpg', 5),
(99, 'https://image.msscdn.net/thumbnails/images/prd_img/20200424/1419869/detail_1419869_5_500.jpg', 6),
(99, 'https://image.msscdn.net/thumbnails/images/prd_img/20200424/1419869/detail_1419869_6_500.jpg', 7),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60da8d748d897.jpg', 8),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60bdf12ddeab7.jpg', 9),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60b6e5149ada6.jpg', 10),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60b047561ceaa.jpg', 11),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60ac4c0aef0a6.jpg', 12),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60ab165754eb6.jpg', 13),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60a7192f2d885.jpg', 14),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60a5be69807fc.jpg', 15),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_60a1c91538373.jpg', 16),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_609df09e4d3b6.jpg', 17),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_609df12f15b93.jpg', 18),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_609df0267a465.jpg', 19),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_609def70ee51d.jpg', 20),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_6098c55cad458.jpg', 21),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_5f06643909efa.jpg', 22),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_5f03c360b2aa9.jpg', 23),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_5efd2f9c09a93.jpg', 24),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_5efa99baa3917.jpg', 25),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_5ef94cddc0110.jpg', 26),
(99, 'https://image.msscdn.net/thumbnails/mfile_s01/_shopstaff/staff_5ef94d150170a.jpg', 27);

-- 타버 컴포트 벨크로 피셔맨 샌들 (세인트새틴)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(100, 'https://image.msscdn.net/thumbnails/images/goods_img/20250328/4958671/4958671_17431361442841_500.png', 1);

-- [슈탠다드] 논슬립 투버클 리커버리 슬리퍼 HPCV5E2502 (슈펜)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(101, 'https://image.msscdn.net/thumbnails/images/goods_img/20240419/4068667/4068667_17473791769304_500.jpg', 1),
(101, 'https://image.msscdn.net/thumbnails/images/prd_img/20240419/4068667/detail_4068667_17471101295580_500.jpg', 2),
(101, 'https://image.msscdn.net/thumbnails/images/prd_img/20240419/4068667/detail_4068667_17471101299215_500.jpg', 3),
(101, 'https://image.msscdn.net/thumbnails/images/prd_img/20240419/4068667/detail_4068667_17471101301705_500.jpg', 4),
(101, 'https://image.msscdn.net/thumbnails/images/prd_img/20240419/4068667/detail_4068667_17471101305282_500.jpg', 5),
(101, 'https://image.msscdn.net/thumbnails/images/prd_img/20240419/4068667/detail_4068667_17146124992374_500.jpg', 6);

-- [리퍼브/단종] KM002 코모레비 뮤지엄 유니섹스 핸드메이드 레더 피셔맨 샌들 l 4Colors (코모레비뮤지엄)
INSERT INTO musinsa_shop.product_images (product_id, image_url, sort_order) values
(102, 'https://image.msscdn.net/thumbnails/images/goods_img/20250613/5182966/5182966_17497898822642_500.jpg', 1),
(102, 'https://image.msscdn.net/thumbnails/images/prd_img/20250613/5182966/detail_5182966_17497898948328_500.jpg', 2),
(102, 'https://image.msscdn.net/thumbnails/images/prd_img/20250613/5182966/detail_5182966_17497899008380_500.jpg', 3),
(102, 'https://image.msscdn.net/thumbnails/images/prd_img/20250613/5182966/detail_5182966_17497899047316_500.jpg', 4),
(102, 'https://image.msscdn.net/thumbnails/images/prd_img/20250613/5182966/detail_5182966_17497899113839_500.jpg', 5);


-- 신발이기 때문에 가상의 데이터 알맞게 넣음
INSERT INTO musinsa_shop.product_options (product_id, size, stock, price) VALUES
(1, '220', 6, 59000),
(1, '230', 42, 59000),
(1, '240', 15, 59000),
(1, '250', 38, 59000),
(1, '260', 1, 59000),
(1, '270', 1, 59000),
(1, '280', 43, 59000),
(2, '220', 29, 81000),
(2, '230', 35, 81000),
(2, '240', 38, 81000),
(2, '250', 33, 81000),
(2, '260', 25, 81000),
(2, '270', 15, 81000),
(2, '280', 37, 81000),
(3, '220', 5, 32240),
(3, '230', 27, 32240),
(3, '240', 13, 32240),
(3, '250', 35, 32240),
(3, '260', 32, 32240),
(3, '270', 37, 32240),
(3, '280', 1, 32240),
(4, '220', 37, 50400),
(4, '230', 50, 50400),
(4, '240', 26, 50400),
(4, '250', 9, 50400),
(4, '260', 50, 50400),
(4, '270', 38, 50400),
(4, '280', 31, 50400),
(5, '220', 5, 62100),
(5, '230', 44, 62100),
(5, '240', 48, 62100),
(5, '250', 18, 62100),
(5, '260', 43, 62100),
(5, '270', 20, 62100),
(5, '280', 23, 62100),
(6, '220', 0, 61200),
(6, '230', 14, 61200),
(6, '240', 19, 61200),
(6, '250', 16, 61200),
(6, '260', 40, 61200),
(6, '270', 13, 61200),
(6, '280', 21, 61200),
(7, '220', 47, 35860),
(7, '230', 18, 35860),
(7, '240', 30, 35860),
(7, '250', 6, 35860),
(7, '260', 32, 35860),
(7, '270', 10, 35860),
(7, '280', 12, 35860),
(8, '220', 26, 119200),
(8, '230', 19, 119200),
(8, '240', 36, 119200),
(8, '250', 31, 119200),
(8, '260', 15, 119200),
(8, '270', 48, 119200),
(8, '280', 24, 119200),
(9, '220', 24, 71200),
(9, '230', 38, 71200),
(9, '240', 9, 71200),
(9, '250', 42, 71200),
(9, '260', 49, 71200),
(9, '270', 18, 71200),
(9, '280', 21, 71200),
(10, '220', 26, 55000),
(10, '230', 32, 55000),
(10, '240', 22, 55000),
(10, '250', 19, 55000),
(10, '260', 16, 55000),
(10, '270', 38, 55000),
(10, '280', 23, 55000),
(11, '220', 10, 23600),
(11, '230', 36, 23600),
(11, '240', 23, 23600),
(11, '250', 15, 23600),
(11, '260', 50, 23600),
(11, '270', 19, 23600),
(11, '280', 13, 23600),
(12, '220', 16, 71200),
(12, '230', 27, 71200),
(12, '240', 49, 71200),
(12, '250', 25, 71200),
(12, '260', 44, 71200),
(12, '270', 26, 71200),
(12, '280', 9, 71200),
(13, '220', 40, 66750),
(13, '230', 30, 66750),
(13, '240', 14, 66750),
(13, '250', 15, 66750),
(13, '260', 6, 66750),
(13, '270', 44, 66750),
(13, '280', 44, 66750),
(14, '220', 47, 27000),
(14, '230', 44, 27000),
(14, '240', 33, 27000),
(14, '250', 4, 27000),
(14, '260', 15, 27000),
(14, '270', 19, 27000),
(14, '280', 23, 27000),
(15, '220', 43, 62230),
(15, '230', 9, 62230),
(15, '240', 0, 62230),
(15, '250', 8, 62230),
(15, '260', 15, 62230),
(15, '270', 2, 62230),
(15, '280', 45, 62230),
(16, '220', 41, 79000),
(16, '230', 37, 79000),
(16, '240', 33, 79000),
(16, '250', 18, 79000),
(16, '260', 32, 79000),
(16, '270', 20, 79000),
(16, '280', 32, 79000),
(17, '220', 14, 39900),
(17, '230', 38, 39900),
(17, '240', 27, 39900),
(17, '250', 36, 39900),
(17, '260', 19, 39900),
(17, '270', 16, 39900),
(17, '280', 33, 39900),
(18, '220', 3, 45040),
(18, '230', 3, 45040),
(18, '240', 34, 45040),
(18, '250', 33, 45040),
(18, '260', 40, 45040),
(18, '270', 11, 45040),
(18, '280', 35, 45040),
(19, '220', 41, 56700),
(19, '230', 22, 56700),
(19, '240', 19, 56700),
(19, '250', 49, 56700),
(19, '260', 31, 56700),
(19, '270', 46, 56700),
(19, '280', 44, 56700),
(20, '220', 46, 311940),
(20, '230', 21, 311940),
(20, '240', 47, 311940),
(20, '250', 17, 311940),
(20, '260', 16, 311940),
(20, '270', 8, 311940),
(20, '280', 19, 311940),
(21, '220', 24, 27370),
(21, '230', 5, 27370),
(21, '240', 19, 27370),
(21, '250', 35, 27370),
(21, '260', 12, 27370),
(21, '270', 3, 27370),
(21, '280', 47, 27370),
(22, '220', 26, 58650),
(22, '230', 31, 58650),
(22, '240', 43, 58650),
(22, '250', 34, 58650),
(22, '260', 7, 58650),
(22, '270', 23, 58650),
(22, '280', 37, 58650),
(23, '220', 13, 179000),
(23, '230', 14, 179000),
(23, '240', 49, 179000),
(23, '250', 46, 179000),
(23, '260', 50, 179000),
(23, '270', 30, 179000),
(23, '280', 0, 179000),
(24, '220', 44, 106390),
(24, '230', 2, 106390),
(24, '240', 29, 106390),
(24, '250', 43, 106390),
(24, '260', 18, 106390),
(24, '270', 47, 106390),
(24, '280', 30, 106390),
(25, '220', 9, 38170),
(25, '230', 43, 38170),
(25, '240', 10, 38170),
(25, '250', 10, 38170),
(25, '260', 30, 38170),
(25, '270', 49, 38170),
(25, '280', 17, 38170),
(26, '220', 26, 21000),
(26, '230', 33, 21000),
(26, '240', 31, 21000),
(26, '250', 15, 21000),
(26, '260', 6, 21000),
(26, '270', 49, 21000),
(26, '280', 20, 21000),
(27, '220', 27, 27990),
(27, '230', 15, 27990),
(27, '240', 10, 27990),
(27, '250', 16, 27990),
(27, '260', 6, 27990),
(27, '270', 35, 27990),
(27, '280', 46, 27990),
(28, '220', 9, 39900),
(28, '230', 43, 39900),
(28, '240', 24, 39900),
(28, '250', 15, 39900),
(28, '260', 50, 39900),
(28, '270', 36, 39900),
(28, '280', 37, 39900),
(29, '220', 24, 33150),
(29, '230', 23, 33150),
(29, '240', 28, 33150),
(29, '250', 49, 33150),
(29, '260', 1, 33150),
(29, '270', 13, 33150),
(29, '280', 7, 33150),
(30, '220', 44, 35860),
(30, '230', 40, 35860),
(30, '240', 31, 35860),
(30, '250', 20, 35860),
(30, '260', 50, 35860),
(30, '270', 18, 35860),
(30, '280', 7, 35860),
(31, '220', 8, 35550),
(31, '230', 34, 35550),
(31, '240', 23, 35550),
(31, '250', 37, 35550),
(31, '260', 38, 35550),
(31, '270', 4, 35550),
(31, '280', 9, 35550),
(32, '220', 39, 29250),
(32, '230', 8, 29250),
(32, '240', 1, 29250),
(32, '250', 20, 29250),
(32, '260', 17, 29250),
(32, '270', 11, 29250),
(32, '280', 19, 29250),
(33, '220', 34, 67680),
(33, '230', 16, 67680),
(33, '240', 21, 67680),
(33, '250', 6, 67680),
(33, '260', 34, 67680),
(33, '270', 1, 67680),
(33, '280', 35, 67680),
(34, '220', 13, 36290),
(34, '230', 9, 36290),
(34, '240', 6, 36290),
(34, '250', 49, 36290),
(34, '260', 42, 36290),
(34, '270', 20, 36290),
(34, '280', 29, 36290),
(35, '220', 30, 143200),
(35, '230', 2, 143200),
(35, '240', 0, 143200),
(35, '250', 1, 143200),
(35, '260', 30, 143200),
(35, '270', 39, 143200),
(35, '280', 0, 143200),
(36, '220', 31, 53400),
(36, '230', 8, 53400),
(36, '240', 21, 53400),
(36, '250', 16, 53400),
(36, '260', 2, 53400),
(36, '270', 46, 53400),
(36, '280', 29, 53400),
(37, '220', 18, 48000),
(37, '230', 36, 48000),
(37, '240', 15, 48000),
(37, '250', 14, 48000),
(37, '260', 32, 48000),
(37, '270', 3, 48000),
(37, '280', 21, 48000),
(38, '220', 49, 122430),
(38, '230', 6, 122430),
(38, '240', 26, 122430),
(38, '250', 11, 122430),
(38, '260', 2, 122430),
(38, '270', 29, 122430),
(38, '280', 33, 122430),
(39, '220', 17, 20090),
(39, '230', 4, 20090),
(39, '240', 18, 20090),
(39, '250', 49, 20090),
(39, '260', 25, 20090),
(39, '270', 14, 20090),
(39, '280', 20, 20090),
(40, '220', 16, 29740),
(40, '230', 43, 29740),
(40, '240', 48, 29740),
(40, '250', 21, 29740),
(40, '260', 43, 29740),
(40, '270', 8, 29740),
(40, '280', 44, 29740),
(41, '220', 25, 74970),
(41, '230', 45, 74970),
(41, '240', 39, 74970),
(41, '250', 32, 74970),
(41, '260', 37, 74970),
(41, '270', 48, 74970),
(41, '280', 13, 74970),
(42, '220', 23, 36820),
(42, '230', 42, 36820),
(42, '240', 50, 36820),
(42, '250', 13, 36820),
(42, '260', 40, 36820),
(42, '270', 4, 36820),
(42, '280', 16, 36820),
(43, '220', 38, 55200),
(43, '230', 22, 55200),
(43, '240', 44, 55200),
(43, '250', 11, 55200),
(43, '260', 9, 55200),
(43, '270', 2, 55200),
(43, '280', 15, 55200),
(44, '220', 20, 33000),
(44, '230', 20, 33000),
(44, '240', 2, 33000),
(44, '250', 41, 33000),
(44, '260', 20, 33000),
(44, '270', 0, 33000),
(44, '280', 20, 33000),
(45, '220', 44, 55440),
(45, '230', 10, 55440),
(45, '240', 40, 55440),
(45, '250', 21, 55440),
(45, '260', 5, 55440),
(45, '270', 3, 55440),
(45, '280', 15, 55440),
(46, '220', 45, 427540),
(46, '230', 15, 427540),
(46, '240', 41, 427540),
(46, '250', 1, 427540),
(46, '260', 36, 427540),
(46, '270', 0, 427540),
(46, '280', 19, 427540),
(47, '220', 25, 33040),
(47, '230', 13, 33040),
(47, '240', 6, 33040),
(47, '250', 0, 33040),
(47, '260', 17, 33040),
(47, '270', 23, 33040),
(47, '280', 29, 33040),
(48, '220', 3, 71820),
(48, '230', 19, 71820),
(48, '240', 1, 71820),
(48, '250', 5, 71820),
(48, '260', 7, 71820),
(48, '270', 47, 71820),
(48, '280', 13, 71820),
(49, '220', 30, 103000),
(49, '230', 21, 103000),
(49, '240', 32, 103000),
(49, '250', 31, 103000),
(49, '260', 17, 103000),
(49, '270', 11, 103000),
(49, '280', 16, 103000),
(50, '220', 34, 109650),
(50, '230', 28, 109650),
(50, '240', 13, 109650),
(50, '250', 37, 109650),
(50, '260', 33, 109650),
(50, '270', 15, 109650),
(50, '280', 18, 109650),
(51, '220', 42, 41250),
(51, '230', 40, 41250),
(51, '240', 12, 41250),
(51, '250', 44, 41250),
(51, '260', 33, 41250),
(51, '270', 42, 41250),
(51, '280', 41, 41250),
(52, '220', 32, 55250),
(52, '230', 40, 55250),
(52, '240', 26, 55250),
(52, '250', 3, 55250),
(52, '260', 31, 55250),
(52, '270', 47, 55250),
(52, '280', 29, 55250),
(53, '220', 0, 37440),
(53, '230', 4, 37440),
(53, '240', 13, 37440),
(53, '250', 10, 37440),
(53, '260', 50, 37440),
(53, '270', 49, 37440),
(53, '280', 31, 37440),
(54, '220', 37, 37800),
(54, '230', 1, 37800),
(54, '240', 39, 37800),
(54, '250', 31, 37800),
(54, '260', 6, 37800),
(54, '270', 42, 37800),
(54, '280', 6, 37800),
(55, '220', 13, 62370),
(55, '230', 36, 62370),
(55, '240', 12, 62370),
(55, '250', 25, 62370),
(55, '260', 1, 62370),
(55, '270', 24, 62370),
(55, '280', 45, 62370),
(56, '220', 28, 51600),
(56, '230', 40, 51600),
(56, '240', 36, 51600),
(56, '250', 17, 51600),
(56, '260', 32, 51600),
(56, '270', 27, 51600),
(56, '280', 36, 51600),
(57, '220', 37, 32310),
(57, '230', 4, 32310),
(57, '240', 23, 32310),
(57, '250', 31, 32310),
(57, '260', 0, 32310),
(57, '270', 13, 32310),
(57, '280', 21, 32310),
(58, '220', 6, 35320),
(58, '230', 31, 35320),
(58, '240', 17, 35320),
(58, '250', 7, 35320),
(58, '260', 49, 35320),
(58, '270', 32, 35320),
(58, '280', 26, 35320),
(59, '220', 20, 43200),
(59, '230', 15, 43200),
(59, '240', 15, 43200),
(59, '250', 38, 43200),
(59, '260', 27, 43200),
(59, '270', 25, 43200),
(59, '280', 23, 43200),
(60, '220', 33, 69000),
(60, '230', 3, 69000),
(60, '240', 23, 69000),
(60, '250', 20, 69000),
(60, '260', 20, 69000),
(60, '270', 46, 69000),
(60, '280', 0, 69000),
(61, '220', 45, 58140),
(61, '230', 19, 58140),
(61, '240', 46, 58140),
(61, '250', 21, 58140),
(61, '260', 46, 58140),
(61, '270', 10, 58140),
(61, '280', 40, 58140),
(62, '220', 8, 162540),
(62, '230', 30, 162540),
(62, '240', 34, 162540),
(62, '250', 17, 162540),
(62, '260', 41, 162540),
(62, '270', 14, 162540),
(62, '280', 37, 162540),
(63, '220', 2, 45900),
(63, '230', 42, 45900),
(63, '240', 48, 45900),
(63, '250', 1, 45900),
(63, '260', 2, 45900),
(63, '270', 34, 45900),
(63, '280', 50, 45900),
(64, '220', 14, 83300),
(64, '230', 50, 83300),
(64, '240', 0, 83300),
(64, '250', 44, 83300),
(64, '260', 43, 83300),
(64, '270', 1, 83300),
(64, '280', 31, 83300),
(65, '220', 17, 98100),
(65, '230', 50, 98100),
(65, '240', 22, 98100),
(65, '250', 45, 98100),
(65, '260', 17, 98100),
(65, '270', 9, 98100),
(65, '280', 7, 98100),
(66, '220', 46, 65100),
(66, '230', 49, 65100),
(66, '240', 7, 65100),
(66, '250', 21, 65100),
(66, '260', 20, 65100),
(66, '270', 42, 65100),
(66, '280', 33, 65100),
(67, '220', 1, 21250),
(67, '230', 33, 21250),
(67, '240', 46, 21250),
(67, '250', 11, 21250),
(67, '260', 32, 21250),
(67, '270', 39, 21250),
(67, '280', 7, 21250),
(68, '220', 22, 44400),
(68, '230', 45, 44400),
(68, '240', 42, 44400),
(68, '250', 18, 44400),
(68, '260', 36, 44400),
(68, '270', 49, 44400),
(68, '280', 48, 44400),
(69, '220', 3, 41300),
(69, '230', 4, 41300),
(69, '240', 20, 41300),
(69, '250', 38, 41300),
(69, '260', 41, 41300),
(69, '270', 1, 41300),
(69, '280', 27, 41300),
(70, '220', 13, 89100),
(70, '230', 47, 89100),
(70, '240', 17, 89100),
(70, '250', 29, 89100),
(70, '260', 12, 89100),
(70, '270', 47, 89100),
(70, '280', 35, 89100),
(71, '220', 16, 82560),
(71, '230', 14, 82560),
(71, '240', 12, 82560),
(71, '250', 5, 82560),
(71, '260', 20, 82560),
(71, '270', 41, 82560),
(71, '280', 19, 82560),
(72, '220', 36, 67490),
(72, '230', 5, 67490),
(72, '240', 34, 67490),
(72, '250', 31, 67490),
(72, '260', 31, 67490),
(72, '270', 21, 67490),
(72, '280', 21, 67490),
(73, '220', 43, 34230),
(73, '230', 38, 34230),
(73, '240', 25, 34230),
(73, '250', 36, 34230),
(73, '260', 10, 34230),
(73, '270', 34, 34230),
(73, '280', 0, 34230),
(74, '220', 49, 36890),
(74, '230', 7, 36890),
(74, '240', 36, 36890),
(74, '250', 6, 36890),
(74, '260', 31, 36890),
(74, '270', 2, 36890),
(74, '280', 27, 36890),
(75, '220', 21, 23400),
(75, '230', 29, 23400),
(75, '240', 20, 23400),
(75, '250', 38, 23400),
(75, '260', 3, 23400),
(75, '270', 23, 23400),
(75, '280', 15, 23400),
(76, '220', 38, 78500),
(76, '230', 5, 78500),
(76, '240', 28, 78500),
(76, '250', 25, 78500),
(76, '260', 33, 78500),
(76, '270', 48, 78500),
(76, '280', 40, 78500),
(77, '220', 13, 69300),
(77, '230', 24, 69300),
(77, '240', 40, 69300),
(77, '250', 33, 69300),
(77, '260', 20, 69300),
(77, '270', 47, 69300),
(77, '280', 40, 69300),
(78, '220', 4, 42130),
(78, '230', 15, 42130),
(78, '240', 27, 42130),
(78, '250', 31, 42130),
(78, '260', 16, 42130),
(78, '270', 48, 42130),
(78, '280', 19, 42130),
(79, '220', 35, 43860),
(79, '230', 26, 43860),
(79, '240', 42, 43860),
(79, '250', 13, 43860),
(79, '260', 9, 43860),
(79, '270', 47, 43860),
(79, '280', 19, 43860),
(80, '220', 4, 49590),
(80, '230', 35, 49590),
(80, '240', 6, 49590),
(80, '250', 47, 49590),
(80, '260', 0, 49590),
(80, '270', 19, 49590),
(80, '280', 23, 49590),
(81, '220', 47, 36350),
(81, '230', 48, 36350),
(81, '240', 13, 36350),
(81, '250', 44, 36350),
(81, '260', 44, 36350),
(81, '270', 35, 36350),
(81, '280', 26, 36350),
(82, '220', 36, 15920),
(82, '230', 6, 15920),
(82, '240', 26, 15920),
(82, '250', 40, 15920),
(82, '260', 47, 15920),
(82, '270', 17, 15920),
(82, '280', 19, 15920),
(83, '220', 10, 62100),
(83, '230', 31, 62100),
(83, '240', 18, 62100),
(83, '250', 28, 62100),
(83, '260', 5, 62100),
(83, '270', 50, 62100),
(83, '280', 44, 62100),
(84, '220', 34, 73450),
(84, '230', 50, 73450),
(84, '240', 40, 73450),
(84, '250', 43, 73450),
(84, '260', 9, 73450),
(84, '270', 38, 73450),
(84, '280', 20, 73450),
(85, '220', 36, 24650),
(85, '230', 6, 24650),
(85, '240', 48, 24650),
(85, '250', 4, 24650),
(85, '260', 40, 24650),
(85, '270', 43, 24650),
(85, '280', 21, 24650),
(86, '220', 19, 116100),
(86, '230', 42, 116100),
(86, '240', 33, 116100),
(86, '250', 0, 116100),
(86, '260', 10, 116100),
(86, '270', 16, 116100),
(86, '280', 7, 116100),
(87, '220', 42, 42210),
(87, '230', 47, 42210),
(87, '240', 6, 42210),
(87, '250', 2, 42210),
(87, '260', 41, 42210),
(87, '270', 46, 42210),
(87, '280', 37, 42210),
(88, '220', 6, 61920),
(88, '230', 3, 61920),
(88, '240', 34, 61920),
(88, '250', 24, 61920),
(88, '260', 27, 61920),
(88, '270', 46, 61920),
(88, '280', 40, 61920),
(89, '220', 28, 118150),
(89, '230', 31, 118150),
(89, '240', 40, 118150),
(89, '250', 40, 118150),
(89, '260', 43, 118150),
(89, '270', 38, 118150),
(89, '280', 21, 118150),
(90, '220', 44, 44880),
(90, '230', 49, 44880),
(90, '240', 26, 44880),
(90, '250', 25, 44880),
(90, '260', 18, 44880),
(90, '270', 3, 44880),
(90, '280', 12, 44880),
(91, '220', 7, 36680),
(91, '230', 23, 36680),
(91, '240', 49, 36680),
(91, '250', 33, 36680),
(91, '260', 10, 36680),
(91, '270', 22, 36680),
(91, '280', 2, 36680),
(92, '220', 14, 34500),
(92, '230', 30, 34500),
(92, '240', 40, 34500),
(92, '250', 31, 34500),
(92, '260', 44, 34500),
(92, '270', 17, 34500),
(92, '280', 7, 34500),
(93, '220', 34, 119200),
(93, '230', 25, 119200),
(93, '240', 9, 119200),
(93, '250', 5, 119200),
(93, '260', 4, 119200),
(93, '270', 45, 119200),
(93, '280', 22, 119200),
(94, '220', 9, 37820),
(94, '230', 6, 37820),
(94, '240', 28, 37820),
(94, '250', 19, 37820),
(94, '260', 47, 37820),
(94, '270', 38, 37820),
(94, '280', 9, 37820),
(95, '220', 21, 38080),
(95, '230', 38, 38080),
(95, '240', 36, 38080),
(95, '250', 39, 38080),
(95, '260', 31, 38080),
(95, '270', 5, 38080),
(95, '280', 48, 38080),
(96, '220', 12, 107100),
(96, '230', 14, 107100),
(96, '240', 41, 107100),
(96, '250', 42, 107100),
(96, '260', 44, 107100),
(96, '270', 29, 107100),
(96, '280', 11, 107100),
(97, '220', 40, 165410),
(97, '230', 33, 165410),
(97, '240', 36, 165410),
(97, '250', 39, 165410),
(97, '260', 7, 165410),
(97, '270', 15, 165410),
(97, '280', 50, 165410),
(98, '220', 18, 67320),
(98, '230', 22, 67320),
(98, '240', 12, 67320),
(98, '250', 28, 67320),
(98, '260', 20, 67320),
(98, '270', 6, 67320),
(98, '280', 14, 67320),
(99, '220', 0, 38850),
(99, '230', 37, 38850),
(99, '240', 17, 38850),
(99, '250', 25, 38850),
(99, '260', 1, 38850),
(99, '270', 43, 38850),
(99, '280', 3, 38850),
(100, '220', 20, 49410),
(100, '230', 3, 49410),
(100, '240', 41, 49410),
(100, '250', 24, 49410),
(100, '260', 10, 49410),
(100, '270', 49, 49410),
(100, '280', 45, 49410),
(101, '220', 22, 16920),
(101, '230', 22, 16920),
(101, '240', 9, 16920),
(101, '250', 27, 16920),
(101, '260', 26, 16920),
(101, '270', 23, 16920),
(101, '280', 32, 16920),
(102, '220', 30, 34400),
(102, '230', 42, 34400),
(102, '240', 34, 34400),
(102, '250', 5, 34400),
(102, '260', 16, 34400),
(102, '270', 30, 34400),
(102, '280', 10, 34400);