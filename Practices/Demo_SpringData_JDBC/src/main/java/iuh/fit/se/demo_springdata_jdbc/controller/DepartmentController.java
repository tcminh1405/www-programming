package iuh.fit.se.demo_springdata_jdbc.controller;


import iuh.fit.se.demo_springdata_jdbc.entity.Department;
import iuh.fit.se.demo_springdata_jdbc.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @GetMapping
    public Iterable<Department> getAll() {
        return departmentRepository.findAll();
    }

    // Lấy tất cả phòng ban + danh sách nhân viên
    @GetMapping("/with-employees")
    public Iterable<Department> getAllDepartmentsWithEmployees() {
        return departmentRepository.findAll();
    }

    @PostMapping
    public Department create(@RequestBody Department d) {
        return departmentRepository.save(d);
    }
}
