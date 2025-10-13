package iuh.fit.se.trancongminh_week07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/home")
public class HomeController {

    public HomeController() {
        super();
    }

    @GetMapping
    public String HomePage(Model model) {
        LocalDate date = LocalDate.now();
        String mess = "Welcome to Thymeleaf ";
        model.addAttribute("message", mess);
        model.addAttribute("date", date);
        return "home";
    }
}
