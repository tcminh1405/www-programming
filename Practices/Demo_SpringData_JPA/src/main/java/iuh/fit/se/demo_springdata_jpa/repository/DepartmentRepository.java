package iuh.fit.se.demo_springdata_jpa.repository;

import iuh.fit.se.demo_springdata_jpa.entity.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Cách 1: Dùng @EntityGraph để tự động load danh sách employees
    @EntityGraph(attributePaths = "employees")
    @Query("SELECT d FROM Department d")
    List<Department> findAllWithEmployees();
}
