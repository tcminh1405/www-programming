package iuh.fit.se.demo_springdata_mongodb.repository;


import iuh.fit.se.demo_springdata_mongodb.DTO.AvgAgeByStatusDTO;
import iuh.fit.se.demo_springdata_mongodb.DTO.AvgSalaryByDeptIdDTO;
import org.bson.Document;
import java.util.List;

public interface EmployeeAnalyticsRepository {
    List<AvgAgeByStatusDTO> getCountandAvgAgeByStatus();
    List<AvgSalaryByDeptIdDTO> getCountandAvgSalaryByDept();
    List<Document> findAllMaxSalaryEmployees();
    List<Document> findAllMaxAgeEmployees();
}
