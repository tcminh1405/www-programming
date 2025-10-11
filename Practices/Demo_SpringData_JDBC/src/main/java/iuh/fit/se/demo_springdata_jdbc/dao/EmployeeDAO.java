package iuh.fit.se.demo_springdata_jdbc.dao;



import iuh.fit.se.demo_springdata_jdbc.entity.Department;
import iuh.fit.se.demo_springdata_jdbc.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAll();

    Employee getById(int id);

    Employee findEmployeeByName(String name);

    List<Employee> findEmployeesByDepartment(Department dp);

    List<Employee> findEmployeesByToSalary(double salary);
}
