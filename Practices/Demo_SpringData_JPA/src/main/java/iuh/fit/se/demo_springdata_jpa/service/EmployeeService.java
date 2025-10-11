package iuh.fit.se.demo_springdata_jpa.service;

import iuh.fit.se.demo_springdata_jpa.entity.Employee;
import iuh.fit.se.demo_springdata_jpa.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Lấy toàn bộ nhân viên
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // Lưu hoặc cập nhật nhân viên
    public Employee save(Employee e) {
        return employeeRepository.save(e);
    }

    // Tìm theo ID
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    // Xóa
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    // Tìm kiếm theo tên chứa từ khóa (không phân biệt hoa thường)
    public List<Employee> findByNameContainingIgnoreCase(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // Tìm theo tuổi >= ? (dùng query đã định nghĩa)
    public List<Employee> findByAgeGreaterThan(int age) {
        return employeeRepository.findByAgeGreaterThan(age);
    }

    // Tìm theo lương >= ?
    public List<Employee> findBySalaryGreaterThan(double minSalary) {
        return employeeRepository.findBySalaryGreaterThan(minSalary);
    }

    public List<Employee> findByDepartmentName(String deptName) {
        return employeeRepository.findByDepartmentName(deptName);
    }


}
