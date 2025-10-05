package iuh.fit.se.demo_springdata_jdbc.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@Table("department")
public class Department {
    @Id
    private Long id;
    private String name;

    // ánh xạ quan hệ 1-n
    @MappedCollection(idColumn = "department_id")
    private Set<Employee> employees;
}
