package iuh.fit.se.trancongminh_tuan01;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/processFormUpload")
public class ProcessFormUpload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("formData.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Lấy dữ liệu từ form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String facebook = request.getParameter("facebook");
        String shortBio = request.getParameter("shortBio");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Result</title></head><body>");
            out.println("<h2>Thông tin đăng ký</h2>");
            out.println("<p>Firstname: " + firstName + "</p>");
            out.println("<p>Lastname: " + lastName + "</p>");
            out.println("<p>Gender: " + gender + "</p>");
            out.println("<p>Date of Birth: " + dob + "</p>");
            out.println("<p>Username: " + username + "</p>");
            out.println("<p>Email: " + email + "</p>");
            out.println("<p>Password: " + password + "</p>");
            out.println("<p>Facebook: " + facebook + "</p>");
            out.println("<p>Short Bio: " + shortBio + "</p>");
            out.println("</body></html>");
        }
    }
}
