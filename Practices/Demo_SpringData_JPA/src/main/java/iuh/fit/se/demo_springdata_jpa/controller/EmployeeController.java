package iuh.fit.se.demo_springdata_jpa.controller;

import iuh.fit.se.demo_springdata_jpa.entity.Employee;
import iuh.fit.se.demo_springdata_jpa.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PostMapping
    public Employee create(@RequestBody Employee e) {
        return employeeService.save(e);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee e) {
        Employee existing = employeeService.getById(id);
        existing.setName(e.getName());
        existing.setSalary(e.getSalary());
        existing.setDob(e.getDob());
        existing.setDepartment(e.getDepartment());
        return employeeService.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @GetMapping("/search/name")
    public List<Employee> searchByName(@RequestParam String name) {
        return employeeService.findByName(name);
    }

    @GetMapping("/search/age")
    public List<Employee> searchByAge(@RequestParam int age) {
        return employeeService.findByAge(age);
    }

    @GetMapping("/search/salary")
    public List<Employee> searchBySalaryRange(@RequestParam double min, @RequestParam double max) {
        return employeeService.findBySalaryRange(min, max);
    }
}
