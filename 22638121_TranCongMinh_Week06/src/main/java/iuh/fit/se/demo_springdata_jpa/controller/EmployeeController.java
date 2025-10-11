package iuh.fit.se.demo_springdata_jpa.controller;

import iuh.fit.se.demo_springdata_jpa.entity.Employee;
import iuh.fit.se.demo_springdata_jpa.service.DepartmentService;
import iuh.fit.se.demo_springdata_jpa.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    //Danh sách nhân viên

    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employees";
    }


     //Hiển thị form thêm nhân viên
    @GetMapping("/add")
    public String showAddForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAll());
        return "employee_form";
    }

    //Lưu nhân viên
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        if (employee.getSalary() == null) {
            employee.setSalary(0.0);
        }
        employeeService.save(employee);
        return "redirect:/web/employees";
    }

    //Hiển thị form chỉnh sửa nhân viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAll());
        return "employee_form";
    }

    //Xóa nhân viên
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/web/employees";
    }

    // Tìm kiếm nhân viên theo tên, tuổi, lương
    // URL mẫu: /web/employees/search?criteria=name&keyword=John
    @GetMapping("/search")
    public String searchEmployees(
            @RequestParam(required = false) String criteria,
            @RequestParam(required = false) String keyword,
            Model model) {

        List<Employee> employees;

        // Nếu người dùng chưa nhập gì
        if (criteria == null || keyword == null || keyword.trim().isEmpty()) {
            employees = employeeService.getAll();
        } else {
            switch (criteria) {
                case "name" -> employees = employeeService.findByNameContainingIgnoreCase(keyword);

                case "age" -> {
                    try {
                        int age = Integer.parseInt(keyword);
                        employees = employeeService.findByAgeGreaterThan(age);
                    } catch (NumberFormatException e) {
                        employees = employeeService.getAll();
                    }
                }

                case "salary" -> {
                    try {
                        double salary = Double.parseDouble(keyword);
                        employees = employeeService.findBySalaryGreaterThan(salary);
                    } catch (NumberFormatException e) {
                        employees = employeeService.getAll();
                    }
                }case "department" -> {
                    try {
                        employees = employeeService.findByDepartmentName(keyword);
                    } catch (NumberFormatException e) {
                        employees = employeeService.getAll();
                    }
                }

                default -> employees = employeeService.getAll();
            }
        }

        model.addAttribute("employees", employees);
        model.addAttribute("criteria", criteria);
        model.addAttribute("keyword", keyword);
        model.addAttribute("resultCount", employees.size());
        return "employees";
    }
}
