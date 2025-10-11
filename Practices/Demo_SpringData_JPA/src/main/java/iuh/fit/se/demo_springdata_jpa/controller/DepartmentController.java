package iuh.fit.se.demo_springdata_jpa.controller;

import iuh.fit.se.demo_springdata_jpa.entity.Department;
import iuh.fit.se.demo_springdata_jpa.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAll();

        // Null-safe: nếu department list hoặc employees null, set mặc định
        if (departments != null) {
            for (Department d : departments) {
                if (d.getEmployees() == null) {
                    d.setEmployees(List.of()); // set danh sách rỗng
                }
            }
        }

        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Department department = new Department();
        department.setEmployees(List.of()); // mặc định rỗng
        model.addAttribute("department", department);
        return "department_form";
    }

    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute("department") Department department) {
        // Null-safe: đảm bảo employees không null trước khi lưu
        if (department.getEmployees() == null) {
            department.setEmployees(List.of());
        }
        departmentService.save(department);
        return "redirect:/web/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Department department = departmentService.getById(id);
        if (department.getEmployees() == null) {
            department.setEmployees(List.of());
        }
        model.addAttribute("department", department);
        return "department_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return "redirect:/web/departments";
    }

    @GetMapping("/view/{id}")
    public String viewDepartment(@PathVariable Long id, Model model) {
        Department department = departmentService.getById(id);
        if (department.getEmployees() == null) {
            department.setEmployees(List.of());
        }
        model.addAttribute("department", department);
        return "department_detail";
    }

    @GetMapping("/search")
    public String searchDepartment(@RequestParam(required = false) String criteria,
                                   @RequestParam(required = false) String keyword, Model model) {

        List<Department> departments;
        if (criteria == null || keyword == null ||keyword.trim().isEmpty()){
             departments = departmentService.getAll();
        } else {
            switch (criteria) {
                case "name" -> departments = departmentService.findByName(keyword);
                case "count" -> {
                    try {
                        int count = Integer.parseInt(keyword);
                        departments = departmentService.findByEmployeeCount(count);
                    } catch (NumberFormatException e) {
                        departments = departmentService.getAll();
                    }
                }
                default -> departments = departmentService.getAll();
            }
        }
        model.addAttribute("departments", departments);
        model.addAttribute("criteria", criteria);
        model.addAttribute("keyword", keyword);
        return "departments";
    }

}
