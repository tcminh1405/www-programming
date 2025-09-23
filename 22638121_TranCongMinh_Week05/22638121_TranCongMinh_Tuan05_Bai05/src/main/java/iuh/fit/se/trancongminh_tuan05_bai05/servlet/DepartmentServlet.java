package iuh.fit.se.trancongminh_tuan05_bai05.servlet;

import iuh.fit.se.trancongminh_tuan05_bai05.dao.DepartmentDAO;
import iuh.fit.se.trancongminh_tuan05_bai05.dao.EmployeeDAO;
import iuh.fit.se.trancongminh_tuan05_bai05.model.Department;
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

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                List<Department> allDepartments = deptDao.getAllDepartments();
                req.setAttribute("departments", allDepartments);
                req.getRequestDispatcher("department-list.jsp").forward(req, resp);
                break;

            case "new":
                req.setAttribute("departments", deptDao.getAllDepartments());
                req.getRequestDispatcher("department-form.jsp").forward(req, resp);
                break;

            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Department dept = deptDao.getById(id);
                req.setAttribute("department", dept);
                req.setAttribute("departments", deptDao.getAllDepartments());
                req.getRequestDispatcher("department-form.jsp").forward(req, resp);
                break;

            case "delete":
                deptDao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("departments");
                break;

            case "searchbyname":
                String deptName = req.getParameter("deptName");
                List<Department> list;
                if (deptName != null){
                    list = deptDao.getByName(deptName);
                } else {
                    list = deptDao.getAllDepartments();
                }
                req.setAttribute("departments", list);
                req.getRequestDispatcher("department-list.jsp").forward(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        int id  = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;

        String name = req.getParameter("name");

        Department dept = new Department(id, name);
        if (id >0){
            deptDao.update(dept);
        } else {
            deptDao.save(dept);
        }
        resp.sendRedirect("departments");
    }
}
