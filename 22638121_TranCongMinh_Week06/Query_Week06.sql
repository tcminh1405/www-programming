CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    salary DOUBLE,
    dob DATE,
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES department(id)
);

INSERT INTO department (name) VALUES 
('IT'),
('HR'),
('Finance'),
('Marketing'),
('Design');

INSERT INTO employee (name, salary, dob, department_id)
VALUES 
('Trần Công Minh', 15000000, '2004-05-14', 1),
('Alex', 20000000, '2000-07-25', 1),
('Lê Văn An', 18000000, '2001-08-14', 2),
('Phạm Quốc Bình', 25000000, '1998-12-03', 3),
('Đỗ Thị Trang', 22000000, '1999-06-18', 4),
('Vũ Đức Huy', 30000000, '1997-03-22', 1),
('Ngô Thanh Tú', 17000000, '2003-11-09', 5),
('Trịnh Mai Linh', 28000000, '1996-01-12', 3),
('Bùi Minh Phúc', 19000000, '2001-04-30', 2),
('Phan Hoàng My', 24000000, '1999-10-20', 4),
('Đặng Anh Dũng', 31000000, '1995-02-14', 1),
('Nguyễn Ngọc Ánh', 26000000, '1998-09-05', 5),
('Trương Khánh Hòa', 21000000, '2002-07-01', 2);
