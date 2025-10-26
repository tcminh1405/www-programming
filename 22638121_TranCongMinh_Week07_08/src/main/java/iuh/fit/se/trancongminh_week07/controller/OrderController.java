package iuh.fit.se.trancongminh_week07.controller;

import iuh.fit.se.trancongminh_week07.entities.Customer;
import iuh.fit.se.trancongminh_week07.entities.Order;
import iuh.fit.se.trancongminh_week07.services.CustomerService;
import iuh.fit.se.trancongminh_week07.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final CustomerService customerService;

    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    // Danh sách đơn hàng
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String listOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    //Xem chi tiết
    @GetMapping("/detail/{id}")
    public String viewOrderDetail(@PathVariable int id, Model model) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        model.addAttribute("order", order);
        model.addAttribute("lines", order.getOrderLines());
        return "order/detail";
    }

    // Hiển thị form thêm
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerService.findAll());
        return "order/add";
    }

    //Lưu đơn hàng mới
    @PostMapping("/add")
    public String addOrder(@ModelAttribute("order") Order order) {
        orderService.create(order);
        return "redirect:/order";
    }

    // Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        model.addAttribute("order", order);
        model.addAttribute("customers", customerService.findAll());
        return "order/edit";
    }

    //Cập nhật đơn hàng
    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable int id, @ModelAttribute("order") Order updatedOrder) {
        orderService.update(id, updatedOrder);
        return "redirect:/order";
    }

    //Xóa
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable int id) {
        orderService.delete(id);
        return "redirect:/order";
    }
}
