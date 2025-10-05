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

    // Tìm kiếm theo tên
    public List<Employee> findByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // Tìm theo tuổi (dùng query đã định nghĩa)
    public List<Employee> findByAge(int age) {
        return employeeRepository.findByAge(age);
    }

    // Tìm theo khoảng lương
    public List<Employee> findBySalaryRange(double min, double max) {
        return employeeRepository.findBySalaryBetween(min, max);
    }
}
