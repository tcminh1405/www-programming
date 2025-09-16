package iuh.fit.se.trancongminh_tuan01;

import jakarta.servlet.*;
import jakarta.servlet.annotation.HandlesTypes;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;

@WebServlet("/secure/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (isLoggedIn) {
            // Người dùng đã đăng nhập, cho phép truy cập vào servlet
            chain.doFilter(request, response);
        } else {
            // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }

    }
}
