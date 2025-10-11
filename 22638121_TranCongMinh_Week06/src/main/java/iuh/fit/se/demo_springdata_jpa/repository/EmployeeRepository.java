package iuh.fit.se.demo_springdata_jpa.repository;

import iuh.fit.se.demo_springdata_jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Tìm danh sách employee theo tên
    List<Employee> findByNameContainingIgnoreCase(String name);

    // Tìm danh sách employee theo tên phòng ban
    @Query("SELECT e FROM Employee e WHERE e.department.name LIKE %:name%")
    List<Employee> findByDepartmentName(@Param("name") String name);

    // Tìm danh sách employee có tuổi >= ?
    @Query("SELECT e FROM Employee e WHERE e.dob IS NOT NULL AND " +
            "(YEAR(CURRENT_DATE) - YEAR(e.dob)) > :age")
    List<Employee> findByAgeGreaterThan(@Param("age") int age);
    // Tìm danh sách employee có lương >= ?
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findBySalaryGreaterThan(@Param("salary") Double salary);
}
