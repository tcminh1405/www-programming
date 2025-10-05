package iuh.fit.se.trancongminh_bai04_iuhbookstore.dao;

import iuh.fit.se.trancongminh_bai04_iuhbookstore.beans.Book;
import iuh.fit.se.trancongminh_bai04_iuhbookstore.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final DBUtil dbUtil;

    public BookDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    // Lấy tất cả sách
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = dbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("image")
                );
                list.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all books", e);
        }
        return list;
    }

    // Lấy sách theo id
    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"),
                            rs.getString("description"),
                            rs.getString("image")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching book by id", e);
        }
        return null;
    }
}
