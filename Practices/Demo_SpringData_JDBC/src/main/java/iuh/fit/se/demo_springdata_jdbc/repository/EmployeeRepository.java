package iuh.fit.se.demo_springdata_jdbc.repository;

import iuh.fit.se.demo_springdata_jdbc.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByNameContaining(String name);
    List<Employee> findBySalaryGreaterThanEqual(Double salary);
}
