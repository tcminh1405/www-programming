package iuh.fit.se.demo_springdata_mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "employees")
public class Employee {

    @Id
    @JsonIgnore  // ẩn không trả về cho client do Mongo quản lý
    private String id;  // Mongodb generate _id

    private String empId;
    private String empName;
    private String email;
    private int age;
    private Double salary;
    private int status;
    private String deptId;

    // Constructors
    public Employee() {
    }

    public Employee(String empId, String empName, String email, int age, Double salary, int status, String deptId) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.status = status;
        this.deptId = deptId;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}