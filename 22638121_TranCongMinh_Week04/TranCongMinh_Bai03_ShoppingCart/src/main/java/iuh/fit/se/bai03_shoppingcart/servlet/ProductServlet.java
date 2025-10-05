package iuh.fit.se.bai03_shoppingcart.servlet;

import iuh.fit.se.bai03_shoppingcart.beans.Product;
import iuh.fit.se.bai03_shoppingcart.dao.ProductDAO;
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

@WebServlet({"/products", "/product"})
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() {
        productDAO = new ProductDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idstr = req.getParameter("id");

        if (idstr != null) {
            int id = Integer.parseInt(idstr); //  Lấy giá trị từ request
            Product product = productDAO.getProductById(id);
            if (product != null) {
                req.setAttribute("product", product);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product-detail.jsp");
                dispatcher.forward(req, resp);
                return; // quan trọng, tránh tiếp tục chạy code load list sản phẩm
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }
        }


        List<Product> products = productDAO.getAllProducts();
        System.out.println("Products size = " + (products != null ? products.size() : "null"));
        for (Product p : products) {
            System.out.println(p.getModel() + " - " + p.getPrice());
        }
        req.setAttribute("products", products);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product-list.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     //   super.doPost(req,resp);
    }
}
