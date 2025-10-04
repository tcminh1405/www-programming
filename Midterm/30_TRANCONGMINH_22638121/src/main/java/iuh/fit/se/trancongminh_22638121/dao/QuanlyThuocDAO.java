package iuh.fit.se.trancongminh_22638121.dao;

import iuh.fit.se.trancongminh_22638121.model.Thuoc;
import iuh.fit.se.trancongminh_22638121.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuanlyThuocDAO {

    private DBUtil dbutil;

    public QuanlyThuocDAO(DataSource dataSource) {
        dbutil = new DBUtil(dataSource);
    }

    // 1. Lấy danh sách tất cả thuốc
    public List<Thuoc> getAllThuoc() {
        List<Thuoc> list = new ArrayList<>();
        String sql = "SELECT * FROM Thuoc";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Thuoc t = new Thuoc();
                t.setMaThuoc(rs.getInt("MATHUOC"));
                t.setTenThuoc(rs.getString("TENTHUOC"));
                t.setGia(rs.getDouble("GIA"));
                t.setNamSX(rs.getInt("NAMSX"));
                t.setMaLoai(rs.getInt("MALOAI"));

                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Lấy danh sách thuốc theo loại
    public List<Thuoc> getThuocTheoLoai(int maLoai) {
        List<Thuoc> list = new ArrayList<>();
        String sql = "SELECT * FROM Thuoc WHERE MALOAI=?";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maLoai);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Thuoc t = new Thuoc();
                    t.setMaThuoc(rs.getInt("MATHUOC"));
                    t.setTenThuoc(rs.getString("TENTHUOC"));
                    t.setGia(rs.getDouble("GIA"));
                    t.setNamSX(rs.getInt("NAMSX"));
                    t.setMaLoai(rs.getInt("MALOAI"));

                    list.add(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. Thêm thuốc mới
    public void save(Thuoc t) {
        String sql = "INSERT INTO Thuoc(TENTHUOC, GIA, NAMSX, MALOAI) VALUES (?,?,?,?)";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getTenThuoc());
            ps.setDouble(2, t.getGia());
            ps.setInt(3, t.getNamSX());
            ps.setInt(4, t.getMaLoai());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Cập nhật thuốc
    public void update(Thuoc t) {
        String sql = "UPDATE Thuoc SET TENTHUOC=?, GIA=?, NAMSX=?, MALOAI=? WHERE MATHUOC=?";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getTenThuoc());
            ps.setDouble(2, t.getGia());
            ps.setInt(3, t.getNamSX());
            ps.setInt(4, t.getMaLoai());
            ps.setInt(5, t.getMaThuoc());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5. Xóa thuốc
    public void delete(int maThuoc) {
        String sql = "DELETE FROM Thuoc WHERE MATHUOC=?";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maThuoc);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Thuoc findById(int id) {
        String sql = "SELECT * FROM Thuoc WHERE maThuoc=?";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Thuoc(
                            rs.getInt("maThuoc"),
                            rs.getString("tenThuoc"),
                            rs.getDouble("gia"),
                            rs.getInt("namSX"),
                            rs.getInt("maLoai")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
