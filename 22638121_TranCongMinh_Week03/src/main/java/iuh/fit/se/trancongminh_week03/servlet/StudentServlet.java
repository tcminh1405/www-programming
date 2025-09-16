package iuh.fit.se.trancongminh_week03.servlet;

import iuh.fit.se.trancongminh_week03.model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    public StudentServlet (){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student student = new Student();
        // Lấy dữ liệu từ form
        student.setFirstname(req.getParameter("firstname"));
        student.setLastname(req.getParameter("lastname"));

        String dob = req.getParameter("dob");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // format của input type="date"
        Date dateOfBirth = null;
        try {
            dateOfBirth = sdf.parse(dob);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        student.setDateofbirth(dateOfBirth);

        student.setEmail(req.getParameter("email"));
        student.setPhone(req.getParameter("phone"));
        student.setGender(req.getParameter("gender"));
        student.setAddress(req.getParameter("address"));
        student.setCity(req.getParameter("city"));
        student.setPincode(req.getParameter("pincode"));
        student.setState(req.getParameter("state"));
        student.setCountry(req.getParameter("country"));

        String[] hobbies = req.getParameterValues("hobbies");
        student.setHobbies((hobbies));

        student.setCourse(req.getParameter("course"));

        req.setAttribute("student", student);
        RequestDispatcher rd = req.getRequestDispatcher("student-result.jsp");
        rd.forward(req,resp);
    }
}
