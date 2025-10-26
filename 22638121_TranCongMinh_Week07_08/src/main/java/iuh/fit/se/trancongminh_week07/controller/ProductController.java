package iuh.fit.se.trancongminh_week07.controller;

import iuh.fit.se.trancongminh_week07.entities.Category;
import iuh.fit.se.trancongminh_week07.entities.Comment;
import iuh.fit.se.trancongminh_week07.entities.Product;
import iuh.fit.se.trancongminh_week07.services.CategoryService;
import iuh.fit.se.trancongminh_week07.services.CommentService;
import iuh.fit.se.trancongminh_week07.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private final ProductService productService;
    private final CommentService commentService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CommentService commentService, CategoryService categoryService) {
        this.productService = productService;
        this.commentService = commentService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showAllProduct(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "product/list"; // trỏ tới file templates/product/list.html
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable int id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        List<Comment> comments = commentService.findByProductId(id);

        model.addAttribute("product", product);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new Comment());
        return "product/productdetail";
    }

    //Form thêm sản phẩm mới
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addProductForm(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("product", new Product());
        return "product/add";
    }

    //Xử lý lưu sản phẩm mới
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Product product) {
        productService.create(product);
        return "redirect:/product";
    }

    //Form sửa sản phẩm
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable int id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        model.addAttribute("product", product);
        return "product/edit"; // -> form chỉnh sửa
    }

    //Cập nhật sản phẩm
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute Product product) {
        productService.update(id, product);
        return "redirect:/product";
    }

    //Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/product";
    }
}
