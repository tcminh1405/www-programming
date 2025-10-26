package iuh.fit.se.trancongminh_week07.controller;

import iuh.fit.se.trancongminh_week07.entities.*;
import iuh.fit.se.trancongminh_week07.services.CustomerService;
import iuh.fit.se.trancongminh_week07.services.OrderService;
import iuh.fit.se.trancongminh_week07.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart") // giỏ hàng lưu trong session
public class CartController {

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;

    // Xem giỏ hàng
    @GetMapping
    public String viewCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("cart", cart);
        return "cart/view";
    }

    // Thêm vào giỏ hàng
    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Integer id,
                            @RequestParam(defaultValue = "1") int quantity,
                            @ModelAttribute("cart") Cart cart) {
        Optional<Product> optionalProduct = productService.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        cart.addItem(product, quantity);
        return "redirect:/cart";
    }

    // Xóa khỏi giỏ hàng
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Integer id, @ModelAttribute("cart") Cart cart) {
        cart.removeItem(id);
        return "redirect:/cart";
    }

    // Thanh toán
    @PostMapping("/checkout")
    public String checkout(@ModelAttribute("cart") Cart cart) {
        if (cart.getItems().isEmpty()) return "redirect:/cart";

        Customer customer = customerService.getCurrentCustomer(); // ví dụ lấy từ user login

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(LocalDate.now());
        order.setOrderLines(
                cart.getItems().stream().map(item -> {
                    OrderLine line = new OrderLine();
                    line.setOrder(order);
                    line.setProduct(item.getProduct());
                    line.setAmount(item.getQuantity());
                    line.setPurchasePrice(item.getPrice());
                    return line;
                }).collect(Collectors.toSet())
        );

        orderService.create(order);
        cart.clear(); // dọn giỏ
        return "redirect:/order";
    }
}
