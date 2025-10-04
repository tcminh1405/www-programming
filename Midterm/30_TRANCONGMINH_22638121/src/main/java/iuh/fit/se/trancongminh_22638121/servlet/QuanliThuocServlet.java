package iuh.fit.se.trancongminh_22638121.servlet;

import iuh.fit.se.trancongminh_22638121.dao.QuanlyLoaiThuocDAO;
import iuh.fit.se.trancongminh_22638121.dao.QuanlyThuocDAO;
import iuh.fit.se.trancongminh_22638121.model.LoaiThuoc;
import iuh.fit.se.trancongminh_22638121.model.Thuoc;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/thuoc")
public class QuanliThuocServlet extends HttpServlet {

    @Resource(name = "jdbc/22638121_trancongminh")
    private DataSource dataSource;

    private QuanlyThuocDAO thuocDAO;
    private QuanlyLoaiThuocDAO loaiThuocDAO;

    @Override
    public void init() throws ServletException {
        thuocDAO = new QuanlyThuocDAO(dataSource);
        loaiThuocDAO = new QuanlyLoaiThuocDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list": // a. Xuất danh sách thuốc
                List<Thuoc> thuocs = thuocDAO.getAllThuoc();
                req.setAttribute("thuocs", thuocs);
                req.getRequestDispatcher("thuoc-list.jsp").forward(req, resp);
                break;

            case "loai": // b. Xuất danh sách các loại thuốc
                List<LoaiThuoc> loaiThuocs = loaiThuocDAO.getAllLoaiThuoc();
                req.setAttribute("loaiThuocs", loaiThuocs);
                req.getRequestDispatcher("loai-thuoc-list.jsp").forward(req, resp);
                break;

            case "byloai": // c. Xuất danh sách thuốc theo loại
                int loaiId = Integer.parseInt(req.getParameter("maloai"));
                List<Thuoc> listByLoai = thuocDAO.getThuocTheoLoai(loaiId);
                req.setAttribute("thuocs", listByLoai);
                req.getRequestDispatcher("thuoc-list.jsp").forward(req, resp);
                break;

            case "new": // d. Hiện form thêm thuốc
                req.setAttribute("loaiThuocs", loaiThuocDAO.getAllLoaiThuoc());
                req.getRequestDispatcher("thuoc-form.jsp").forward(req, resp);
                break;
            case "edit": // ✅ hiện form sửa thuốc
                int idEdit = Integer.parseInt(req.getParameter("id"));
                Thuoc t = thuocDAO.findById(idEdit);
                req.setAttribute("thuoc", t);
                req.setAttribute("loaiThuocs", loaiThuocDAO.getAllLoaiThuoc());
                req.getRequestDispatcher("thuoc-form.jsp").forward(req, resp);
                break;

            case "delete": // ✅ xóa thuốc
                int idDel = Integer.parseInt(req.getParameter("id"));
                thuocDAO.delete(idDel);
                resp.sendRedirect("thuoc?action=list");
                break;
            default:
                resp.sendRedirect("thuoc?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = req.getParameter("id") == null || req.getParameter("id").isEmpty()
                ? 0 : Integer.parseInt(req.getParameter("id"));
        String ten = req.getParameter("ten");
        double gia = Double.parseDouble(req.getParameter("gia"));
        int namSX = Integer.parseInt(req.getParameter("namsx"));
        int maloai = Integer.parseInt(req.getParameter("maloai"));

        Thuoc t = new Thuoc(id, ten, gia, namSX, maloai);

        if (id == 0) { // thêm mới
            thuocDAO.save(t);
        } else { // cập nhật
            thuocDAO.update(t);
        }

        resp.sendRedirect("thuoc?action=list");
    }
}
