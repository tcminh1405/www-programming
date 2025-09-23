package iuh.fit.se.trancongminh_tuan05_bai05.dao;

import iuh.fit.se.trancongminh_tuan05_bai05.model.Department;
import iuh.fit.se.trancongminh_tuan05_bai05.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private DBUtil dbUtil;

    public DepartmentDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    // lay tat ca phong ban

    public List<Department> getAllDepartments() {
        List<Department> deptlist = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Department dept = new Department();
                dept.setId(rs.getInt("id"));
                dept.setName(rs.getString("name"));
                deptlist.add(dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deptlist;
    }


    //lay ds phong ban theo tu khoa
    public List<Department> getByName(String keyword) {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments WHERE name LIKE ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Department(
                            rs.getInt("id"),
                            rs.getString("name")
                    ));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    //Them phong ban moi
    public void save(Department dept) {
        String sql = "INSERT INTO departments(id, name) VALUES(?,?)";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, dept.getId());
            ps.setString(2, dept.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Cap nhat phong ban
    public void update(Department dept) {
        String sql = "UPDATE departments SET name=? WHERE id=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,dept.getName());
            ps.setInt(2, dept.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //xoa phong ban
    public void delete(int id) {
        String sql = "DELETE FROM departments WHERE id=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Department getById(int id) {
        Department dept = null;
        String sql = "SELECT * FROM departments WHERE id=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dept = new Department(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dept;
    }
}
