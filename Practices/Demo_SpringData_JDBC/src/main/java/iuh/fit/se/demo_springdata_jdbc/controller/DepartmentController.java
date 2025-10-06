package iuh.fit.se.demo_springdata_jdbc.controller;

import iuh.fit.se.demo_springdata_jdbc.entity.Department;
import iuh.fit.se.demo_springdata_jdbc.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    // 1. Lấy tất cả phòng ban
    @GetMapping
    public Iterable<Department> getAll() {
        return departmentRepository.findAll();
    }

    // 2. Lấy phòng ban theo ID
    @GetMapping("/{id}")
    public Optional<Department> getById(@PathVariable Long id) {
        return departmentRepository.findById(id);
    }

    // 3. Thêm phòng ban mới
    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    // 4. Cập nhật phòng ban
    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        return departmentRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedDepartment.getName());
                    return departmentRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng ban có id = " + id));
    }

    // 5. Xóa phòng ban
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentRepository.deleteById(id);
    }

    // 6. Hiển thị tất cả phòng ban kèm danh sách nhân viên (thể hiện quan hệ 1-n)
    @GetMapping("/with-employees")
    public Iterable<Department> getAllDepartmentsWithEmployees() {
        return departmentRepository.findAll();
    }

    // 7. Tìm phòng ban có đúng N nhân viên
    @GetMapping("/by-employee-count")
    public List<Department> getByEmployeeCount(@RequestParam int count) {
        List<Long> ids = departmentRepository.findDepartmentIdsByEmployeeCount(count);
        return StreamSupport.stream(departmentRepository.findAllById(ids).spliterator(), false)
                .collect(Collectors.toList());
    }
}
