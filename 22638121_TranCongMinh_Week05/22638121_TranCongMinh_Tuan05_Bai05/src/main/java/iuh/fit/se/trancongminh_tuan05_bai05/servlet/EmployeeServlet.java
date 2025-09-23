package iuh.fit.se.trancongminh_tuan05_bai05.servlet;

import iuh.fit.se.trancongminh_tuan05_bai05.dao.DepartmentDAO;
import iuh.fit.se.trancongminh_tuan05_bai05.dao.EmployeeDAO;
import iuh.fit.se.trancongminh_tuan05_bai05.model.Employee;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

    @Resource(name = "jdbc/hrdb")
    private DataSource dataSource;

    private EmployeeDAO empDao;
    private DepartmentDAO deptDao;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        try {
            empDao = new EmployeeDAO(dataSource);
            deptDao = new DepartmentDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException("Không thể khởi tạo DAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                // Lấy tất cả nhân viên
                List<Employee> allEmployees = empDao.getAllEmployees();
                req.setAttribute("employees", allEmployees);
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;

            case "new":
                // Form thêm mới nhân viên
                req.setAttribute("departments", deptDao.getAllDepartments());
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;

            case "edit":
                // Lấy nhân viên theo id để sửa
                int id = Integer.parseInt(req.getParameter("id"));
                Employee emp = empDao.getById(id);
                req.setAttribute("employee", emp);
                req.setAttribute("departments", deptDao.getAllDepartments());
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;

            case "delete":
                empDao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("employees");
                break;

            case "viewbyid":
                // Lọc nhân viên theo phòng ban
                String deptId = req.getParameter("deptId");
                List<Employee> list;
                if (deptId != null) {
                    list = empDao.getAllByDepartment(Integer.parseInt(deptId));
                } else {
                    list = empDao.getAllByDepartment(1); // mặc định dept = 1
                }
                req.setAttribute("employees", list);
                req.setAttribute("departments", deptDao.getAllDepartments());
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;

            default:
                resp.sendRedirect("employees?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;

        String name = req.getParameter("name");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int deptId = Integer.parseInt(req.getParameter("departmentId"));

        Employee emp = new Employee(id, name, deptId, salary);
        if (id > 0) {
            empDao.update(emp);
        } else {
            empDao.save(emp);
        }

        resp.sendRedirect("employees?deptId=" + deptId);
    }
}
