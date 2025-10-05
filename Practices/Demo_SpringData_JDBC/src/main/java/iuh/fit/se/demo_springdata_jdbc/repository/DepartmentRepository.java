package iuh.fit.se.demo_springdata_jdbc.repository;

import iuh.fit.se.demo_springdata_jdbc.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
