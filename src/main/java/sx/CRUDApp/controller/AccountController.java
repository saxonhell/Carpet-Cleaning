package sx.CRUDApp.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sx.CRUDApp.models.Employee;
import sx.CRUDApp.models.Role;
import sx.CRUDApp.repo.EmployeeRepo;
import sx.CRUDApp.repo.RoleRepo;
import sx.CRUDApp.service.EmployeeService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/acc")
public class AccountController {
    private final EmployeeService employeeService;
    private final RoleRepo roleRepo;

    @Autowired
    public AccountController(EmployeeService employeeService, RoleRepo roleRepo) {
        this.employeeService = employeeService;
        this.roleRepo = roleRepo;
    }


    @GetMapping
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            boolean hasUsernameCookie = Arrays.stream(cookies)
                    .anyMatch(cookie -> "username".equals(cookie.getName()));

            if (!hasUsernameCookie) {
                return "redirect:/acc/login";
            }
        }
        return "account/accountPage";
    }

    @GetMapping("/reg")
    public String registration(@ModelAttribute("employee") Employee employee, Model model) {
        populateRoles(model);
        return "auth/reg";
    }

    @PostMapping("/reg")
    public String registration(HttpServletResponse response,
                               @ModelAttribute("employee") @Valid Employee employee,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            populateRoles(model);
            return "auth/reg";
        }

        int roleId = employee.getRole().getId();
        Optional<Role> role = roleRepo.findById(roleId);
        if (role.isPresent()) {
            employee.setRole(role.get());
        } else {
            bindingResult.rejectValue("role", "error.employee", "Invalid role selected");
            populateRoles(model);
            return "auth/reg";
        }

        employeeService.save(employee);

        Cookie cookie = new Cookie("username", employee.getUsername());
        cookie.setMaxAge(60 * 60 * 24); // 1 день
        response.addCookie(cookie);

        return "redirect:/acc";
    }

    private void populateRoles(Model model) {
        List<Role> roles = roleRepo.findAll().stream()
                .filter(role -> !"ADMIN".equals(role.getName()))
                .collect(Collectors.toList());
        model.addAttribute("roles", roles);
    }

    @GetMapping("/login")
    public String authentication(@ModelAttribute("employee") Employee employee){
        return "auth/login";
    }

    @PostMapping("/login")
    public String authentication(@ModelAttribute("employee") Employee employee,
                                 BindingResult bindingResult,
                                 HttpServletResponse response){
        if (bindingResult.hasErrors()) {
            return "redirect:/acc/auth";
        }

        Cookie cookie = new Cookie("username", employee.getUsername());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        //todo сделать аутентификацию

        return "account/accountPage";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("employee") Employee employee,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  "redirect:/acc";
        }

        employeeService.deleteById(employee.getId());

        return "redirect:/acc";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("employee") Employee employee,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/acc";
        }

        employeeService.updateEmployee(employee);
        return "redirect:/acc";
    }
}