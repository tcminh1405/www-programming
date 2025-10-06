package iuh.fit.se.demo_springdata_jdbc.repository;

import iuh.fit.se.demo_springdata_jdbc.entity.Department;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    // Tìm phòng ban có đúng số lượng nhân viên = count
    @Query("""
        SELECT d.id 
        FROM department d
        LEFT JOIN employee e ON e.department_id = d.id
        GROUP BY d.id
        HAVING COUNT(e.id) = :count
    """)
    List<Long> findDepartmentIdsByEmployeeCount(@Param("count") int count);
}
