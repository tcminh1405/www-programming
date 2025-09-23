package iuh.fit.se.trancongminh_tuan05_bai06.servlet;

import iuh.fit.se.trancongminh_tuan05_bai06.dao.DanhSachTinTucQuanLy;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/quanlytintuc")
public class QuanLyFormServlet extends HttpServlet {
    @Resource(name = "jdbc/quanlydanhmuc")
    private DataSource dataSource;

    private DanhSachTinTucQuanLy dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new DanhSachTinTucQuanLy(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int maTT = Integer.parseInt(req.getParameter("maTT"));
        int maDM = Integer.parseInt(req.getParameter("maDM"));

        dao.deleteTinTuc(maTT);

        resp.sendRedirect("danhsachtintuc?maDM=" + maDM);
    }
}
