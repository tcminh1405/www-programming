//package iuh.fit.se.demo_springdata_jdbc.service;
//
//import iuh.fit.se.demo_springdata_jdbc.dao.EmployeeDAO;
//import iuh.fit.se.demo_springdata_jdbc.entity.Department;
//import iuh.fit.se.demo_springdata_jdbc.entity.Employee;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class EmployeeService {
//
//    private final EmployeeDAO employeeDAO;
//
//    // Constructor Injection
//    public EmployeeService(EmployeeDAO employeeDAO) {
//        this.employeeDAO = employeeDAO;
//    }
//
//    // Lấy tất cả nhân viên
//    public List<Employee> getAllEmployees() {
//        return employeeDAO.getAll();
//    }
//
//    // Tìm nhân viên theo ID
//    public Employee getEmployeeById(int id) {
//        return employeeDAO.getById(id);
//    }
//
//    // Tìm nhân viên theo tên
//    public Employee findEmployeeByName(String name) {
//        return employeeDAO.findEmployeeByName(name);
//    }
//
//    // Tìm tất cả nhân viên thuộc một phòng ban
//    public List<Employee> findEmployeesByDepartment(Department dp) {
//        return employeeDAO.findEmployeesByDepartment(dp);
//    }
//
//    // Tìm tất cả nhân viên có lương = mức salary
//    public List<Employee> findEmployeesByToSalary(double salary) {
//        return employeeDAO.findEmployeesByToSalary(salary);
//    }
//}
