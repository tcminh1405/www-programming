package iuh.fit.se.trancongminh_week03.util;

import iuh.fit.se.trancongminh_week03.model.Account;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountUtil {

    private DataSource dataSource;

    public AccountUtil(DataSource dataSource) throws Exception {
        this.dataSource = dataSource;
    }

    // Lấy danh sách account
    public List<Account> getAccounts() throws Exception {
        List<Account> accounts = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            System.out.println("✅ Kết nối MariaDB thành công: " + conn.getMetaData().getURL());
            String sql = "SELECT * FROM accounts ORDER BY ID";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("ID");
                String fname = rs.getString("FIRSTNAME");
                String lname = rs.getString("LASTNAME");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                Date dateofbirth = rs.getDate("DATEOFBIRTH");

                Account acc = new Account(id,fname,lname,email,password,(java.sql.Date)dateofbirth);
                accounts.add(acc);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }

    // Thêm account mới
    public void addAccount(Account acc)  throws Exception{
        String sql = "INSERT INTO accounts (FIRSTNAME, LASTNAME, EMAIL, PASSWORD, DATEOFBIRTH) " +
                 "VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

       try {
           conn = dataSource.getConnection();
           System.out.println("✅ Kết nối MariaDB thành công: " + conn.getMetaData().getURL());
           ps = conn.prepareStatement(sql);
           ps.setString(1, acc.getFirstname());
           ps.setString(2, acc.getLastname());
           ps.setString(3, acc.getEmail());
           ps.setString(4, acc.getPassword());
           ps.setDate(5, (Date) acc.getDateOfBirth());
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }
}
