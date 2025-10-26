package iuh.fit.se.trancongminh_week07.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Shopping System!");
        model.addAttribute("date", java.time.LocalDate.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Lấy tên user đăng nhập hiện tại
        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/auth/login")
    public String loginPage() {
        return "auth/login"; // khớp với đường dẫn template
    }
}

