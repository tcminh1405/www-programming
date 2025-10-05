package iuh.fit.se.demo_springdata_jdbc.controller;


import iuh.fit.se.demo_springdata_jdbc.entity.Employee;
import iuh.fit.se.demo_springdata_jdbc.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping
    public Iterable<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    @GetMapping("/search")
    public List<Employee> searchByName(@RequestParam String name) {
        return employeeRepository.findByNameContaining(name);
    }

    @GetMapping("/filter-salary")
    public List<Employee> filterBySalary(@RequestParam Double minSalary) {
        return employeeRepository.findBySalaryGreaterThanEqual(minSalary);
    }
    @GetMapping("/filter-age")
    public List<Employee> filterByAge(@RequestParam int minAge) {
        List<Employee> all = (List<Employee>) employeeRepository.findAll();
        LocalDate now = LocalDate.now();
        return all.stream()
                .filter(e -> e.getDob() != null && Period.between(e.getDob(), now).getYears() >= minAge)
                .toList();
    }

}
