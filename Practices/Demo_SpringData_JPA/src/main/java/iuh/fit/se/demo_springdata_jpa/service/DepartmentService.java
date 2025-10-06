package iuh.fit.se.demo_springdata_jpa.service;

import iuh.fit.se.demo_springdata_jpa.entity.Department;
import iuh.fit.se.demo_springdata_jpa.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // Lấy tất cả phòng ban
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    // Lấy theo ID
    public Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    // Lưu hoặc cập nhật
    public Department save(Department d) {
        return departmentRepository.save(d);
    }

    // Xóa
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    // Lấy tất cả phòng ban kèm danh sách nhân viên
    public List<Department> getAllWithEmployees() {
        return departmentRepository.findAllWithEmployees();
    }

    // Lấy phòng ban có số lượng nhân viên bằng count
    public List<Department> getDepartmentsByEmployeeCount(int count) {
        return departmentRepository.findByEmployeeCount(count);
    }
}
