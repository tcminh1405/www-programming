package iuh.fit.se.demo_springdata_mongodb.service;


import iuh.fit.se.demo_springdata_mongodb.DTO.AvgAgeByStatusDTO;
import iuh.fit.se.demo_springdata_mongodb.DTO.AvgSalaryByDeptIdDTO;
import iuh.fit.se.demo_springdata_mongodb.model.Employee;
import iuh.fit.se.demo_springdata_mongodb.repository.EmployeeAnalyticsRepository;
import iuh.fit.se.demo_springdata_mongodb.repository.EmployeeRepository;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;
    private final EmployeeAnalyticsRepository analyticsRepo;

    public EmployeeServiceImpl(EmployeeRepository repo, EmployeeAnalyticsRepository analyticsRepo) {
        this.repo = repo;
        this.analyticsRepo = analyticsRepo;
    }

    @Override
    public List<Employee> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Employee> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public Employee save(Employee emp) {
        return repo.save(emp);
    }

    @Override
    public Employee update(String id, Employee emp) {
        Optional<Employee> opt = repo.findById(id);
        if (opt.isPresent()) {
            Employee exist = opt.get();
            exist.setEmpId(emp.getEmpId());
            exist.setEmpName(emp.getEmpName());
            exist.setEmail(emp.getEmail());
            exist.setAge(emp.getAge());
            exist.setSalary(emp.getSalary());
            exist.setStatus(emp.getStatus());
            exist.setDeptId(emp.getDeptId());
            return repo.save(exist);
        } else {
            emp.setId(null);
            return repo.save(emp);
        }
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Employee findByEmpId(String empId) {
        return repo.findByEmpId(empId);
    }

    @Override
    public List<Employee> findByName(String name) {
        return repo.findByEmpNameContainingIgnoreCase(name);
    }

    @Override
    public List<Employee> findByDeptId(String deptId) {
        return repo.findByDeptId(deptId);
    }

    @Override
    public List<Employee> findByDeptIdOrderBySalaryAsc(String deptId) {
        return repo.findByDeptIdOrderBySalaryAsc(deptId);
    }

    @Override
    public List<AvgAgeByStatusDTO> getCountandAvgAgeByStatus() {
        return analyticsRepo.getCountandAvgAgeByStatus();
    }

    @Override
    public List<AvgSalaryByDeptIdDTO> getCountandAvgSalaryByDept() {
        return analyticsRepo.getCountandAvgSalaryByDept();
    }

    @Override
    public List<Document> findAllMaxSalaryEmployees() {
        return analyticsRepo.findAllMaxSalaryEmployees();
    }

    @Override
    public List<Document> findAllMaxAgeEmployees() {
        return analyticsRepo.findAllMaxAgeEmployees();
    }
}
