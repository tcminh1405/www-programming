package iuh.fit.se.bai03_shoppingcart.dao;

import iuh.fit.se.bai03_shoppingcart.beans.Product;
import iuh.fit.se.bai03_shoppingcart.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DBUtil dbUtil;

    public ProductDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    //read all
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM products";
        try (Connection conn = dbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String model = rs.getString("MODEL");
                Double price = rs.getDouble("PRICE");
                Integer quantity = rs.getInt("QUANTITY");
                String image = rs.getString("IMGURL");
                String description = rs.getString("DESCRIPTION");

                Product p = new Product(id, model, description, quantity, price, image);
                list.add(p);
                System.out.println("co sp");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return list;
    }

    //read by id
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE ID=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Integer proid = rs.getInt("ID");
                    String model = rs.getString("MODEL");
                    Double price = rs.getDouble("PRICE");
                    Integer quantity = rs.getInt("QUANTITY");
                    String image = rs.getString("IMGURL");
                    String description = rs.getString("DESCRIPTION");

                    Product p = new Product(proid, model, description,quantity, price, image);
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
