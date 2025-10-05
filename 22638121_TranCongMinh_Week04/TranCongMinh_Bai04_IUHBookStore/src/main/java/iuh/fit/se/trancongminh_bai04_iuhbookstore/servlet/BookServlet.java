package iuh.fit.se.trancongminh_bai04_iuhbookstore.servlet;

import iuh.fit.se.trancongminh_bai04_iuhbookstore.beans.Book;
import iuh.fit.se.trancongminh_bai04_iuhbookstore.dao.BookDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet({"/books", "/book"})
public class BookServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() {
        bookDAO = new BookDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");

        if (idStr != null) {
            // Chi tiết sách
            int id = Integer.parseInt(idStr);
            Book book = bookDAO.getBookById(id);

            if (book != null) {
                req.setAttribute("book", book);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/chitietsach.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
            }
        } else {
            // Danh sách sách
            List<Book> books = bookDAO.getAllBooks();
            req.setAttribute("books", books);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/danhsach.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
