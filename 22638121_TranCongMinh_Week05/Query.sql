quanlydanhmucquanlydanhmucCREATE DATABASE IF NOT EXISTS hrdb;
USE hrdb;

CREATE TABLE departments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department_id INT,
    salary double,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- Thêm dữ liệu mẫu vào bảng departments
INSERT INTO departments (id, name) VALUES
(1, 'Nhân sự'),
(2, 'Kinh doanh'),
(3, 'Công nghệ');

-- Thêm dữ liệu mẫu vào bảng employees
INSERT INTO employees (id, name, department_id, salary) VALUES
(1, 'Nguyễn Văn A', 1, 15000000.00),
(2, 'Trần Thị B', 2, 20000000.00),
(3, 'Lê Văn C', 3, 25000000.00),
(4, 'Phạm Thị D', 1, 18000000.00),
(5, 'Hoàng Văn E', 3, 22000000.00);



CREATE DATABASE QUANLYDANHMUC;
USE QUANLYDANHMUC;

CREATE TABLE DANHMUC (
    MADM INT PRIMARY KEY AUTO_INCREMENT,
    TENDANHMUC VARCHAR(100) NOT NULL,
    NGUOIQUANLY VARCHAR(100),
    GHICHU VARCHAR(255)
);

CREATE TABLE TINTUC (
    MATT INT PRIMARY KEY AUTO_INCREMENT,
    TIEUDE VARCHAR(200) NOT NULL,
    NOIDUNGTT VARCHAR(255) NOT NULL,
    LIENKET VARCHAR(200) NOT NULL,
    MADM INT,
    FOREIGN KEY (MADM) REFERENCES DANHMUC(MADM)
);

INSERT INTO DANHMUC (TENDANHMUC, NGUOIQUANLY, GHICHU)
VALUES ('Công nghệ', 'Trần Công Minh', 'Tin tức về CNTT'),
       ('Thể thao', 'Nguyễn Văn A', 'Tin bóng đá, thể thao');

INSERT INTO TINTUC (TIEUDE, NOIDUNGTT, LIENKET, MADM)
VALUES ('Ra mắt iPhone 17 series', 'Apple vừa ra mắt iPhone 17 series...', 'http://apple.com/iphone17', 1),
       ('Việt Nam thắng Thái Lan', 'Tỉ số 2-1 cho Việt Nam...', 'http://bongda.com/vn-thang-thai', 2);
