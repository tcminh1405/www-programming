package iuh.fit.se.trancongminh_week03.servlet;

import iuh.fit.se.trancongminh_week03.model.Account;
import iuh.fit.se.trancongminh_week03.util.AccountUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/registerform")
public class AccountRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountUtil accountUtil;
    @Resource(name = "jdbc/storedb")
    private DataSource dataSource;

    public void init(ServletConfig config) throws ServletException {
        try {
            accountUtil = new AccountUtil(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        java.sql.Date dob = Date.valueOf(req.getParameter("dateofbirth"));

        Account account = new Account(firstname, lastname, email, password, (java.sql.Date) dob);

        try {
            accountUtil.addAccount(account);
            List<Account> accounts = accountUtil.getAccounts();

            req.setAttribute("accounts", accounts);
            RequestDispatcher rd = req.getRequestDispatcher("account.jsp");
            rd.forward(req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


         }
}
