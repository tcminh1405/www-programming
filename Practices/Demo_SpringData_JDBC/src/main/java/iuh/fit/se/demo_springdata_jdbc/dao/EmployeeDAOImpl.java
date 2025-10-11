//package iuh.fit.se.demo_springdata_jdbc.dao;
//
//import iuh.fit.se.demo_springdata_jdbc.entity.Department;
//import iuh.fit.se.demo_springdata_jdbc.entity.Employee;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class EmployeeDAOImpl implements EmployeeDAO {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate) {
//
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    // ===== RowMapper: map Employee + Department =====
//    private final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
//        Department dept = new Department();
//        dept.setDeptId(rs.getLong("deptId"));
//        dept.setDeptName(rs.getString("deptName"));
//
//        Employee emp = new Employee();
//        emp.setEmpId(rs.getLong("empId"));
//        emp.setEmpName(rs.getString("empName"));
//        emp.setDob(rs.getDate("dob").toLocalDate());
//        emp.setSalary(rs.getDouble("salary"));
//        emp.setDept(dept);
//
//        return emp;
//    };
//
//    @Override
//    public List<Employee> getAll() {
//        String sql = """
//            SELECT e.empId, e.empName, e.dob, e.salary,
//                   d.deptId, d.deptName
//            FROM employees e
//            JOIN departments d ON e.deptId = d.deptId
//            """;
//        return jdbcTemplate.query(sql, employeeRowMapper);
//    }
//
//    @Override
//    public Employee getById(int id) {
//        String sql = """
//            SELECT e.empId, e.empName, e.dob, e.salary,
//                   d.deptId, d.deptName
//            FROM employees e
//            JOIN departments d ON e.deptId = d.deptId
//            WHERE e.empId = ?
//            """;
//        return jdbcTemplate.queryForObject(sql, employeeRowMapper, id);
//    }
//
//    @Override
//    public Employee findEmployeeByName(String name) {
//        String sql = """
//            SELECT e.empId, e.empName, e.dob, e.salary,
//                   d.deptId, d.deptName
//            FROM employees e
//            JOIN departments d ON e.deptId = d.deptId
//            WHERE e.empName LIKE ?
//            """;
//        List<Employee> result = jdbcTemplate.query(sql, employeeRowMapper, "%" + name + "%");
//        return result.isEmpty() ? null : result.get(0);
//    }
//
//    @Override
//    public List<Employee> findEmployeesByDepartment(Department dp) {
//        String sql = """
//            SELECT e.empId, e.empName, e.dob, e.salary,
//                   d.deptId, d.deptName
//            FROM employees e
//            JOIN departments d ON e.deptId = d.deptId
//            WHERE d.deptId = ?
//            """;
//        return jdbcTemplate.query(sql, employeeRowMapper, dp.getDeptId());
//    }
//
//    @Override
//    public List<Employee> findEmployeesByToSalary(double salary) {
//        String sql = """
//            SELECT e.empId, e.empName, e.dob, e.salary,
//                   d.deptId, d.deptName
//            FROM employees e
//            JOIN departments d ON e.deptId = d.deptId
//            WHERE e.salary <= ?
//            """;
//        return jdbcTemplate.query(sql, employeeRowMapper, salary);
//    }
//}
