package iuh.fit.se.trancongminh_22638121.dao;

import iuh.fit.se.trancongminh_22638121.model.LoaiThuoc;
import iuh.fit.se.trancongminh_22638121.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuanlyLoaiThuocDAO {
    private DBUtil dbutil;

    public QuanlyLoaiThuocDAO(DataSource dataSource) {
        this.dbutil = new DBUtil(dataSource);
    }

    public List<LoaiThuoc> getAllLoaiThuoc() {
        List<LoaiThuoc> list = new ArrayList<>();
        String sql = "SELECT * FROM loaithuoc";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new LoaiThuoc(
                        rs.getInt("maloai"),
                        rs.getString("tenloai")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
