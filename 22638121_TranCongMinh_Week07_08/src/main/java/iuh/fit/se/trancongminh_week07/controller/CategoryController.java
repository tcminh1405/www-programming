package iuh.fit.se.trancongminh_week07.controller;

import iuh.fit.se.trancongminh_week07.entities.Category;
import iuh.fit.se.trancongminh_week07.entities.Product;
import iuh.fit.se.trancongminh_week07.services.CategoryService;
import iuh.fit.se.trancongminh_week07.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @GetMapping
    public String showAllCategory(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        return "category/list";
    }

    // Hiển thị form thêm mới
    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add"; // => templates/category/add.html
    }

    //Lưu category mới
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.create(category);
        return "redirect:/category";
    }

    //Hiển thị form sửa danh mục
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable int id, Model model) {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        model.addAttribute("category", category);
        return "category/edit";
    }

    //Cập nhật danh mục
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable int id, @ModelAttribute("category") Category updatedCategory) {
        categoryService.update(id, updatedCategory);
        return "redirect:/category";
    }

    // Hiển thị chi tiết category (và danh sách product)
    @GetMapping("/{id:\\d+}")
    public String showCategoryDetail(@PathVariable int id, Model model) {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        List<Product> products = category.getProducts();

        model.addAttribute("category", category);
        model.addAttribute("products", products);
        model.addAttribute("newProduct", new Product());

        return "category/categorydetail"; // => templates/category/categorydetail.html
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.delete(id);
        return "redirect:/category";
    }
}
