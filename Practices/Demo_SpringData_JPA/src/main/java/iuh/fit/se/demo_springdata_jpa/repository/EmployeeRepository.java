package iuh.fit.se.demo_springdata_jpa.repository;

import iuh.fit.se.demo_springdata_jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String name);

    @Query("SELECT e FROM Employee e WHERE YEAR(CURRENT_DATE) - YEAR(e.dob) = ?1")
    List<Employee> findByAge(int age);

    List<Employee> findBySalaryBetween(double min, double max);
}
