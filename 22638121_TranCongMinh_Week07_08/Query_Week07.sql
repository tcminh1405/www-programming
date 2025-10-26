-- Xóa bảng cũ (đảm bảo tạo mới sạch sẽ)
DROP TABLE IF EXISTS comments, orderlines, orders, products, categories, customers;
-- Dữ liệu mẫu
INSERT INTO categories (name) VALUES
('Laptop'), ('Phone'), ('Accessory');

INSERT INTO customers (name, customer_since) VALUES
('Tran Cong Minh', '2004-05-14'),
('Kyung Minn', '2004-02-18');

INSERT INTO products (name, price, in_stock, category_id) VALUES
('Dell XPS 13', 20000000.00, 1, 1),
('MacBook Air M4', 23000000.00, 1, 1),
('iPhone 17 Pro Max', 30000000.00, 1, 2),
('Logitech MX Master 3S', 1700000.00, 1, 3),
('Asus ROG Zephyrus G14', 1500.00.00, 1, 1),
('Samsung Galaxy S24 Ultra', 1200.00, 1, 2),
('Sony WH-1000XM5 Headphones', 350.00, 1, 3);

INSERT INTO orders (date, customer_id) VALUES
('2025-06-12', 1),
('2025-07-02', 2);

INSERT INTO orderlines (product_id, amount, purchase_price, order_id) VALUES
(1, 1, 1200.00, 1),
(3, 1, 999.00, 1),
(2, 1, 1300.00, 2),
(4, 2, 99.00, 2);

INSERT INTO comments (text, product_id) VALUES
('Very good performance!', 1),
('Love the battery life.', 2),
('Camera is amazing!', 3),
('Nice design.', 4),
('Keyboard is very comfortable.', 1),
('Amazing display quality.', 2),
('The camera is unbeatable!', 3),
('Wireless connection is stable.', 4),
('Gaming performance top-tier.', 5),
('Battery lasts all day.', 6),
('Sound quality excellent.', 7);



USE shoppingdb;

-- Xóa bảng cũ (nếu có)
DROP TABLE IF EXISTS comments, orderlines, orders, products, categories, customers;


-- Danh mục sản phẩm
INSERT INTO categories (name) VALUES
('Laptop'),
('Phone'),
('Accessory');

-- Khách hàng
INSERT INTO customers (name, customer_since) VALUES
('Trần Công Minh', '2004-05-14'),
('Kyung Minn', '2004-02-18');

-- Sản phẩm (giá VNĐ)
INSERT INTO products (name, price, in_stock, category_id) VALUES
('Dell XPS 13', 20000000, 1, 1),
('MacBook Air M4', 23000000, 1, 1),
('iPhone 17 Pro Max', 30000000, 1, 2),
('Logitech MX Master 3S', 1700000, 1, 3),
('Asus ROG Zephyrus G14', 34000000, 1, 1),
('Samsung Galaxy S24 Ultra', 32000000, 1, 2),
('Sony WH-1000XM5 Headphones', 8500000, 1, 3);

-- Đơn hàng
INSERT INTO orders (date, customer_id) VALUES
('2025-06-12', 1),
('2025-07-02', 2);

-- Chi tiết đơn hàng
INSERT INTO orderlines (product_id, amount, purchase_price, order_id) VALUES
(1, 1, 20000000, 1),
(3, 1, 30000000, 1),
(2, 1, 23000000, 2),
(4, 2, 1700000, 2);

-- Bình luận sản phẩm
INSERT INTO comments (text, product_id) VALUES
('Hiệu năng tuyệt vời, rất đáng tiền!', 1),
('Pin dùng lâu, sạc nhanh.', 2),
('Camera cực kỳ sắc nét.', 3),
('Thiết kế nhỏ gọn, cầm chắc tay.', 4),
('Bàn phím gõ rất êm.', 1),
('Màn hình hiển thị siêu đẹp.', 2),
('Chụp đêm rất tốt.', 3),
('Kết nối không dây ổn định.', 4),
('Chạy game cực mượt.', 5),
('Pin trâu, sạc nhanh.', 6),
('Âm thanh chất lượng cao.', 7);



