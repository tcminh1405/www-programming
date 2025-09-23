package iuh.fit.se.trancongminh_tuan05_bai06.servlet;

import iuh.fit.se.trancongminh_tuan05_bai06.dao.DanhSachTinTucQuanLy;
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

@WebServlet("/tintucform")
public class TinTucFormServlet extends HttpServlet {
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
        req.getRequestDispatcher("TinTucForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String tieuDe = req.getParameter("tieuDe");
        String noiDung = req.getParameter("noiDungTT");
        String lienKet = req.getParameter("lienKet");
        int maDM = Integer.parseInt(req.getParameter("maDM"));

        TinTuc t = new TinTuc(0, tieuDe, noiDung, lienKet, maDM);
        dao.addTinTuc(t);

        resp.sendRedirect("danhsachtintuc?maDM=" + maDM);
    }
}
