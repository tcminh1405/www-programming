package iuh.fit.se.trancongminh_week07.controller;

import iuh.fit.se.trancongminh_week07.entities.Customer;
import iuh.fit.se.trancongminh_week07.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //Hiển thị danh sách khách hàng
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String showAllCustomers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

    //Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/add";
    }

    //Lưu khách hàng mới
    @PostMapping("/add")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.create(customer);
        return "redirect:/customer";
    }

    //Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    //Cập nhật thông tin
    @PostMapping("/edit/{id}")
    public String updateCustomer(@PathVariable int id, @ModelAttribute("customer") Customer updatedCustomer) {
        customerService.update(id, updatedCustomer);
        return "redirect:/customer";
    }

    //Xem chi tiết khách hàng
    @GetMapping("/{id:\\d+}")
    public String showDetail(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        model.addAttribute("customer", customer);
        return "customer/detail";
    }

    //Xóa khách hàng
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
        return "redirect:/customer";
    }
}
