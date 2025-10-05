package iuh.fit.se.demo_springdata_mongodb.repository;


import iuh.fit.se.demo_springdata_mongodb.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findAll();

    Employee findByEmpId(String empId);

    List<Employee> findByEmpNameContainingIgnoreCase(String name);

    List<Employee> findByStatus(int status);

    List<Employee> findByDeptId(String deptId);

    List<Employee> findByDeptIdOrderBySalaryAsc(String deptId);
}
