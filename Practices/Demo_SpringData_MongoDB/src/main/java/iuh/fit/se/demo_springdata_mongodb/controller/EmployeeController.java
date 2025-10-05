package iuh.fit.se.demo_springdata_mongodb.controller;


import iuh.fit.se.demo_springdata_mongodb.DTO.AvgAgeByStatusDTO;
import iuh.fit.se.demo_springdata_mongodb.DTO.AvgSalaryByDeptIdDTO;
import iuh.fit.se.demo_springdata_mongodb.model.Employee;
import iuh.fit.se.demo_springdata_mongodb.service.EmployeeService;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService svc;

    public EmployeeController(EmployeeService svc) {
        this.svc = svc;
    }

    // CRUD
    @GetMapping
    public List<Employee> getAll() {
        return svc.findAll();
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee emp) {
        Employee created = svc.save(emp);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable String id) {
        return svc.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable String id, @RequestBody Employee emp) {
        Employee updated = svc.update(id, emp);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }

    // tìm theo empId
    @GetMapping("/by-empId/{empId}")
    public Employee findByEmpId(@PathVariable String empId) {
        return svc.findByEmpId(empId);
    }

    // tên chứa
    @GetMapping("/search")
    public List<Employee> searchByName(@RequestParam String name) {
        return svc.findByName(name);
    }

    // theo dept
    @GetMapping("/department/{deptId}")
    public List<Employee> getByDept(@PathVariable String deptId) {
        return svc.findByDeptId(deptId);
    }

    // danh sách theo dept sắp xếp salary tăng dần
    @GetMapping("/department/{deptId}/by-salary-asc")
    public List<Employee> getByDeptSalaryAsc(@PathVariable String deptId) {
        return svc.findByDeptIdOrderBySalaryAsc(deptId);
    }

    // employees có salary cao nhất
    @GetMapping("/max-salary")
    public List<Document> getMaxSalaryEmployees() {
        return svc.findAllMaxSalaryEmployees();
    }

    // employees có age cao nhất
    @GetMapping("/max-age")
    public List<Document> getMaxAgeEmployees() {
        return svc.findAllMaxAgeEmployees();
    }

    // thống kê: số employee và avg salary theo dept
    @GetMapping("/analytics/by-dept")
    public List<AvgSalaryByDeptIdDTO> getCountAndAvgSalaryByDept() {
        return svc.getCountandAvgSalaryByDept();
    }

    // thống kê: số employee và avg age theo status
    @GetMapping("/analytics/by-status")
    public List<AvgAgeByStatusDTO> getCountAndAvgAgeByStatus() {
        return svc.getCountandAvgAgeByStatus();
    }
}
