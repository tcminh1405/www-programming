package iuh.fit.se.trancongminh_tuan05_bai06.dao;

import iuh.fit.se.trancongminh_tuan05_bai06.model.DanhMuc;
import iuh.fit.se.trancongminh_tuan05_bai06.model.TinTuc;
import iuh.fit.se.trancongminh_tuan05_bai06.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanhSachTinTucQuanLy {
    private DBUtil dbutil;

    public DanhSachTinTucQuanLy(DataSource dataSource) {
        dbutil = new DBUtil(dataSource);
    }

    // Lấy tin tức theo danh mục
    public List<TinTuc> getTinTucByDanhMuc(int maDM) throws SQLException {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM TINTUC WHERE MADM=?";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDM);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TinTuc(
                        rs.getInt("MATT"),
                        rs.getString("TIEUDE"),
                        rs.getString("NOIDUNGTT"),
                        rs.getString("LIENKET"),
                        rs.getInt("MADM")
                ));
            }
        }
        return list;
    }


    // Thêm mới tin tức
    public void addTinTuc(TinTuc t) {
        String sql = "INSERT INTO TinTuc(TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?,?,?,?)";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getTieuDe());
            ps.setString(2, t.getNoiDungTT());
            ps.setString(3, t.getLienKet());
            ps.setInt(4, t.getMaDM());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa tin tức
    public void deleteTinTuc(int maTT) {
        String sql = "DELETE FROM TinTuc WHERE MATT=?";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTT);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DanhMuc> getAllDanhMuc() throws SQLException {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM DANHMUC";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DanhMuc(
                        rs.getInt("MADM"),
                        rs.getString("TENDANHMUC"),
                        rs.getString("NGUOIQUANLY"),
                        rs.getString("GHICHU")
                ));
            }
        }
        return list;
    }

}
