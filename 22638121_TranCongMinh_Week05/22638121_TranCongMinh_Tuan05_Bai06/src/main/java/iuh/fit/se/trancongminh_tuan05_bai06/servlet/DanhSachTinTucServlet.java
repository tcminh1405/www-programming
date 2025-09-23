package iuh.fit.se.trancongminh_tuan05_bai06.servlet;

import iuh.fit.se.trancongminh_tuan05_bai06.dao.DanhSachTinTucQuanLy;
import iuh.fit.se.trancongminh_tuan05_bai06.model.DanhMuc;
import iuh.fit.se.trancongminh_tuan05_bai06.model.TinTuc;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/danhsachtintuc")
public class DanhSachTinTucServlet extends HttpServlet {
    private DanhSachTinTucQuanLy dao;

    @Resource(name = "jdbc/quanlydanhmuc")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        dao = new DanhSachTinTucQuanLy(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy tất cả danh mục
            List<DanhMuc> dsDanhMuc = dao.getAllDanhMuc();
            req.setAttribute("dsDanhMuc", dsDanhMuc);

            // Nếu có chọn danh mục thì lấy tin tức của danh mục đó
            String maDMParam = req.getParameter("maDM");
            if (maDMParam != null) {
                int maDM = Integer.parseInt(maDMParam);
                List<TinTuc> listTinTuc = dao.getTinTucByDanhMuc(maDM);
                req.setAttribute("listTinTuc", listTinTuc);
                req.setAttribute("maDMChon", maDM);
            }

            req.getRequestDispatcher("DanhSachTinTuc.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

