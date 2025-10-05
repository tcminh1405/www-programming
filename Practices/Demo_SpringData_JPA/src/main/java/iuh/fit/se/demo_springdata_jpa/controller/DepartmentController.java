package iuh.fit.se.demo_springdata_jpa.controller;

import iuh.fit.se.demo_springdata_jpa.entity.Department;
import iuh.fit.se.demo_springdata_jpa.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // Lấy tất cả phòng ban
    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    // Lấy 1 phòng ban theo id
    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    // Lấy tất cả phòng ban + danh sách nhân viên
    @GetMapping("/with-employees")
    public List<Department> getAllWithEmployees() {
        return departmentService.getAllWithEmployees();
    }

    // Thêm phòng ban
    @PostMapping
    public Department create(@RequestBody Department d) {
        return departmentService.save(d);
    }

    // Cập nhật phòng ban
    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department d) {
        Department existing = departmentService.getById(id);
        existing.setName(d.getName());
        return departmentService.save(existing);
    }

    // Xóa phòng ban
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }
}
