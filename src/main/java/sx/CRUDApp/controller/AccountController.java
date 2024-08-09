package sx.CRUDApp.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sx.CRUDApp.models.Employee;
import sx.CRUDApp.repo.EmployeeRepo;

import java.io.Console;
import java.util.Arrays;

@Controller
@RequestMapping("/acc")
public class AccountController {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public AccountController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
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
    public String registration(@ModelAttribute("employee") Employee employee){
        return "auth/reg";
    }

    @PostMapping("/reg")
    public String registration(HttpServletResponse response,
                               @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:auth/reg";
        }

        employeeRepo.save(employee);
        Cookie cookie = new Cookie("username", employee.getUsername());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/acc";
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
        //todo сделать авторизацию

        return "account/accountPage";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("employee") Employee employee,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:/acc";
        }

        employeeRepo.deleteById(employee.getId());

        return "redirect:/acc";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("employee") Employee employee,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/acc";
        }

        employeeRepo.updateEmployee(employee.getId(), employee.getFio(), employee.getPhoneNumber(), employee.getRole(), employee.getUsername());
        return "redirect:/acc";
    }
}

