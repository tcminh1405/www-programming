package iuh.fit.se.trancongminh_tuan05_bai05.model;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private int departmentId;

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, String name, int departmentId, double salary) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    // Getter, Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

}
