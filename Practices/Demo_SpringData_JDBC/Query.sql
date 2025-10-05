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

INSERT INTO department (name) VALUES ('IT'), ('HR'), ('Finance');

INSERT INTO employee (name, salary, dob, department_id)
VALUES 
('Minh', 1500, '2002-05-10', 1),
('Lan', 2000, '2000-07-25', 1),
('An', 1800, '2001-08-14', 2);
