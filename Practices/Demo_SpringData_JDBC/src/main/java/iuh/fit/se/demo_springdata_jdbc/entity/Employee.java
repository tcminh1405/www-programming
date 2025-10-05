package iuh.fit.se.demo_springdata_jdbc.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("employee")
public class Employee {
    @Id
    private Long id;
    private String name;
    private Double salary;
    private LocalDate dob;

    @Column("department_id")
    private Long departmentId;
}
