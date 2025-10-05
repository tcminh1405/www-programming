package iuh.fit.se.demo_springdata_mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    private String id;
    private String deptId;
    private String name;
}
