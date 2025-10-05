package iuh.fit.se.demo_springdata_mongodb.repository;


import iuh.fit.se.demo_springdata_mongodb.DTO.AvgAgeByStatusDTO;
import iuh.fit.se.demo_springdata_mongodb.DTO.AvgSalaryByDeptIdDTO;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class EmployeeAnalyticsRepositoryImpl implements EmployeeAnalyticsRepository {

    private final MongoTemplate mongoTemplate;

    public EmployeeAnalyticsRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<AvgAgeByStatusDTO> getCountandAvgAgeByStatus() {
        Aggregation agg = newAggregation(
                group("status")
                        .count().as("count")
                        .avg("age").as("avgAge"),
                project("count", "avgAge").and("_id").as("status")
        );

        AggregationResults<AvgAgeByStatusDTO> results =
                mongoTemplate.aggregate(agg, "employees", AvgAgeByStatusDTO.class);
        return results.getMappedResults();
    }

    @Override
    public List<AvgSalaryByDeptIdDTO> getCountandAvgSalaryByDept() {
        Aggregation agg = newAggregation(
                group("deptId")
                        .count().as("count")
                        .avg("salary").as("avgSalary"),
                project("count", "avgSalary").and("_id").as("deptId")
        );

        AggregationResults<AvgSalaryByDeptIdDTO> results =
                mongoTemplate.aggregate(agg, "employees", AvgSalaryByDeptIdDTO.class);
        return results.getMappedResults();
    }

    @Override
    public List<Document> findAllMaxSalaryEmployees() {
        Aggregation agg = newAggregation(
                group().max("salary").as("maxSalary"),
                lookup("employees", "maxSalary", "salary", "employees"),
                unwind("employees"),
                replaceRoot("employees")
        );

        AggregationResults<Document> res = mongoTemplate.aggregate(agg, "employees", Document.class);
        return res.getMappedResults();
    }

    @Override
    public List<Document> findAllMaxAgeEmployees() {
        Aggregation agg = newAggregation(
                group().max("age").as("maxAge"),
                lookup("employees", "maxAge", "age", "employees"),
                unwind("employees"),
                replaceRoot("employees")
        );

        AggregationResults<Document> res = mongoTemplate.aggregate(agg, "employees", Document.class);
        return res.getMappedResults();
    }
}
