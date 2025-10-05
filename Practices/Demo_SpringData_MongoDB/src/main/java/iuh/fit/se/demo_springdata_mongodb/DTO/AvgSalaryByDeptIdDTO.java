package iuh.fit.se.demo_springdata_mongodb.DTO;

import lombok.Data;

@Data
public class AvgSalaryByDeptIdDTO {
    private String deptId;
    private Long count;
    private Double avgSalary;
}
