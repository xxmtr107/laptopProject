-- CHÈN DỮ LIỆU CHO BRANDS (HÃNG)
INSERT INTO brands (brand_name) VALUES
                                    (N'Apple'), (N'Dell'), (N'HP'), (N'Lenovo'), (N'Asus'), (N'Acer'), (N'MSI');

-- CHÈN DỮ LIỆU CHO LAPTOP_SERIES (DÒNG MÁY)
INSERT INTO laptop_series (series_name, brand_id) VALUES
                                                      (N'MacBook Air', 1), (N'MacBook Pro', 1);
-- Dell (brand_id = 2)
INSERT INTO laptop_series (series_name, brand_id) VALUES
                                                      (N'XPS', 2), (N'Inspiron', 2), (N'Vostro', 2), (N'Latitude', 2), (N'Alienware', 2);
-- HP (brand_id = 3)
INSERT INTO laptop_series (series_name, brand_id) VALUES
                                                      (N'Pavilion', 3), (N'Envy', 3), (N'Spectre', 3), (N'Victus', 3), (N'ProBook', 3);
-- Lenovo (brand_id = 4)
INSERT INTO laptop_series (series_name, brand_id) VALUES
                                                      (N'ThinkPad', 4), (N'ThinkBook', 4), (N'IdeaPad', 4), (N'Yoga', 4), (N'Legion', 4), (N'LOQ', 4), (N'V Series', 4);
-- Asus (brand_id = 5)
INSERT INTO laptop_series (series_name, brand_id) VALUES
                                                      (N'VivoBook', 5), (N'ZenBook', 5), (N'ROG', 5), (N'TUF', 5), (N'ExpertBook', 5);
-- Acer (brand_id = 6)
INSERT INTO laptop_series (series_name, brand_id) VALUES
                                                      (N'Aspire', 6), (N'Swift', 6), (N'Predator', 6), (N'Nitro', 6);
-- MSI (brand_id = 7)
INSERT INTO laptop_series (series_name, brand_id) VALUES
                                                      (N'Raider', 7), (N'Cyborg', 7), (N'Katana', 7), (N'Modern', 7), (N'Prestige', 7), (N'Venture', 7);

INSERT INTO laptops (name, price, image, description, stock, brand_id, series_id) VALUES
                                                                                      (N'MacBook Air 13-inch M2 2022', 24990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/2/4/24_131.jpg', N'<p>Chip <b>M2</b> mạnh mẽ, thiết kế mỏng nhẹ.</p><ul><li>RAM 8GB</li><li>SSD 256GB</li></ul>', 50, 1, 1),
                                                                                      (N'MacBook Pro 14-inch M3 2023', 39990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_560_1_.png', N'<ul><li>Chip M3 mới nhất</li><li>Màn hình Liquid Retina XDR 120Hz</li></ul>', 30, 1, 2),
                                                                                      (N'Dell XPS 13 Plus 9320', 32500000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_17__5.png', N'<h3>Thiết kế tương lai</h3><p>Viền màn hình siêu mỏng, bàn phím tràn cạnh.</p>', 25, 2, 3),
                                                                                      (N'Dell Inspiron 15 3530 i7', 18990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/l/a/laptop-dell-inspiron-15-3530-i5-n5i5791w1_6__1_3.png', N'<p>Laptop văn phòng phổ thông, cấu hình Intel Gen 13.</p>', 100, 2, 4),
                                                                                      (N'Dell Alienware m16 R1', 55990000, 'https://i.dell.com/is/image/DellContent/content/dam/ss2/product-images/dell-client-products/notebooks/alienware-notebooks/alienware-m16-intel/media-gallery/ir-perkey/notebook-alienware-m16-black-gallery-1.psd?fmt=png-alpha&pscan=auto&scl=1&hei=402&wid=538&qlt=100,1&resMode=sharp2&size=538,402&chrss=full', N'<p>Laptop gaming cao cấp, tản nhiệt hiệu quả, RTX 4070.</p>', 15, 2, 7),
                                                                                      (N'HP Pavilion 15 eg2082TU', 14590000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/2/_/2_443_2_2_1.png', N'<p>Giá tốt, phù hợp sinh viên, chip Intel Core i5 1240P.</p>', 75, 3, 8),
                                                                                      (N'HP Victus 16 d1110TX', 24990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/2/7/27_2_56.jpg', N'<p>Gaming thiết kế lịch lãm, RTX 3050Ti.</p>', 40, 3, 11),
                                                                                      (N'Lenovo ThinkPad X1 Carbon Gen 11', 45000000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/l/a/laptop-lenovo-thinkpad-x1-carbon-gen-11-21hm-000rus_2_.png', N'<p>Bền bỉ, bảo mật, bàn phím huyền thoại cho doanh nhân.</p>', 40, 4, 13),
                                                                                      (N'Lenovo Legion 5 15IAH7', 26990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/l/a/laptop-lenovo-scs_6__2.png', N'<p>Laptop gaming cấu hình cao, tản nhiệt mát mẻ, RTX 3050Ti.</p>', 22, 4, 17),
                                                                                      (N'Asus TUF Gaming F15 FX507ZC4', 19990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_509_4__1.png', N'<p>Gaming tầm trung, chuẩn độ bền quân đội.</p>', 60, 5, 23),
                                                                                      (N'Asus Zenbook 14 OLED Q409ZA', 16990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/l/a/laptop-acer-swift-go-14-ai-sfg14-73-71zx_17_.png', N'<p>Màn hình <b>OLED 2.8K 90Hz</b> siêu đẹp, mỏng nhẹ.</p>', 35, 5, 21),
                                                                                      (N'Acer Aspire 3 A315 58 54XF', 9990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/l/a/laptop-acer-swift-go-14-ai-sfg14-73-71zx_35__2.png', N'<p>Giá rẻ nhất cho Core i5, phù hợp học tập cơ bản.</p>', 120, 6, 25),
                                                                                      (N'Acer Nitro 5 Gaming AN515 58', 21490000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_d_i_7_4.png', N'<p>Laptop gaming quốc dân, p/p tốt, RTX 3050.</p>', 55, 6, 28),
                                                                                      (N'MSI Modern 14 C13M', 13490000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_2__2_51.png', N'<p>Mỏng nhẹ, vỏ nhôm, cấu hình i5 Gen 13 mới.</p>', 48, 7, 32),
                                                                                      (N'MSI Katana GF66 12UCK', 17990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/h/thi_t_k_ch_a_c_t_n_1_2.png', N'<p>Thiết kế gaming, bàn phím LED đỏ, RTX 3050.</p>', 45, 7, 31);