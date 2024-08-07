INSERT INTO user_auth (name, email, password) VALUES ('システム管理者', 'info@aaa.com', 'himitu');
INSERT INTO user_auth (name, email, password) VALUES ('田中一郎', 'tanaka@aaa.com', 'himitu');

INSERT INTO company (name, zipcode, address, phone, url, industry, priority) VALUES ('共和商事', '340-0822', '埼玉県八潮市大瀬2-1-3',  '045-0000-0000', 'http://www.kyowa.co.jp', '不動産', 3);
INSERT INTO company (name, zipcode, address, phone, url, industry, priority) VALUES ('野田工務店', '153-0065', '東京都目黒区中町2丁目４－１１',  '03-3333-3333', 'http://noda=constructor.co.jp', '建築デザイン', 5);
INSERT INTO company (name, zipcode, address, phone, url, industry, priority) VALUES ('三和鉄鋼', '676-0074', '兵庫県高砂市梅井2丁目11番6号',  '079-111-1111', 'http://www.sanwa.steel.com', '鉄鋼業', 4);
INSERT INTO company (name, zipcode, address, phone, url, industry, priority) VALUES ('有限会社アスクミー', '141-0033', '東京都品川区西品川1-2-30 品川スクエア3階',  '03-5555-5555', 'http://www.askme.co.jp', 'ECコンサルタント事業', 2);
INSERT INTO company (name, zipcode, address, phone, url, industry, priority) VALUES ('吉田商店', '151-0071', '東京都渋谷区本町2-13-1 グランドモール1F',  '03-2222-2222', 'http://www.yoshida-luntons.com', '提灯の製造・販売', 3);
INSERT INTO company (name, zipcode, address, phone, url, industry, priority) VALUES ('株式会社トラスト', '577-0015', '大阪府東大阪市長田3-6-4',  '06-6666-6666', 'http://www.trsust.co.jp', '空調工事・電気工事', 4);
INSERT INTO company (name, zipcode, address, phone, url, industry, priority) VALUES ('株式会社サンライズ', '156-0042', '東京都世田谷区羽根木1-12-13',  '03-1234-5678', 'http://sunrise.com', '環境エネルギー事業', 2);
INSERT INTO company (name, zipcode, address, phone, url, industry, priority) VALUES ('共栄コーポレーション', '124-0011', '東京都葛飾区四つ木3-33-15',  '03-3456-9876', 'http://kyoei-corpo.com', '包装用品の企画・制作', 3);

INSERT INTO contact (name, company_id, phone, email) VALUES ('山田太郎', 7, '080-3454-8761', 'yamada@example.com');
INSERT INTO contact (name, company_id, phone, email) VALUES ('鈴木花子', 4, '0120-444-555', 'suzuki@example.com');
INSERT INTO contact (name, company_id, phone, email) VALUES ('吉田義男', 6, '045-666-7890', 'yoshida@askme.co.jp');
INSERT INTO contact (name, company_id, phone, email) VALUES ('佐藤由恵', 4, '090-3100-4480', 'sato-yoshie@askme.co.jp');
INSERT INTO contact (name, company_id, phone, email) VALUES ('伊藤恵子', 3, '070-2234-5632', 'ito-keiko@sanwatekkou.co.jp');
