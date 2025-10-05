package iuh.fit.se.demo_springdata_mongodb.service;


import iuh.fit.se.demo_springdata_mongodb.DTO.AvgAgeByStatusDTO;
import iuh.fit.se.demo_springdata_mongodb.DTO.AvgSalaryByDeptIdDTO;
import iuh.fit.se.demo_springdata_mongodb.model.Employee;
import org.bson.Document;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    Optional<Employee> findById(String id);
    Employee save(Employee emp);
    Employee update(String id, Employee emp);
    void delete(String id);
    Employee findByEmpId(String empId);
    List<Employee> findByName(String name);
    List<Employee> findByDeptId(String deptId);
    List<Employee> findByDeptIdOrderBySalaryAsc(String deptId);

    List<AvgAgeByStatusDTO> getCountandAvgAgeByStatus();
    List<AvgSalaryByDeptIdDTO> getCountandAvgSalaryByDept();
    List<Document> findAllMaxSalaryEmployees();
    List<Document> findAllMaxAgeEmployees();
}
