package iuh.fit.se.trancongminh_tuan01;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public LoginServlet() {

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //  username = admin, password = 123
        if ("admin".equals(username) && "123".equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            res.sendRedirect(req.getContextPath() + "/secure/home.jsp");
        } else {
            req.setAttribute("error","Sai tai khoan");
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
    }
}
