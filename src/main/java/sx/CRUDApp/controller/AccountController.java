package sx.CRUDApp.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sx.CRUDApp.models.Auth;
import sx.CRUDApp.models.Employee;
import sx.CRUDApp.models.Role;
import sx.CRUDApp.repo.RoleRepo;
import sx.CRUDApp.service.EmployeeService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/acc")
public class AccountController {
    private final EmployeeService employeeService;
    private final RoleRepo roleRepo;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AccountController(EmployeeService employeeService, RoleRepo roleRepo, AuthenticationManager authenticationManager) {
        this.employeeService = employeeService;
        this.roleRepo = roleRepo;
        this.authenticationManager = authenticationManager;
    }


    @GetMapping
    public String index(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> usernameCookie = Arrays.stream(cookies)
                    .filter(cookie -> "username".equals(cookie.getName()))
                    .findFirst();

            if (usernameCookie.isEmpty()) {
                return "redirect:/acc/login";
            }

            model.addAttribute("employee", employeeService.findByName(usernameCookie.get().getValue()));
        } else {
            return "redirect:/acc/login";
        }

        return "account/accountPage";
    }

    @GetMapping("/reg")
    public String registration(@ModelAttribute("employee") Employee employee, Model model){
        populateRoles(model);
        return "auth/register";
    }

    @PostMapping("/reg")
    public String registration(HttpServletResponse response,
                               @ModelAttribute("employee") @Valid Employee employee,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            populateRoles(model);
            return "auth/register";
        }

        int roleId = employee.getRole().getId();
        Optional<Role> role = roleRepo.findById(roleId);
        if (role.isPresent()) {
            employee.setRole(role.get());
        } else {
            bindingResult.rejectValue("role", "error.employee", "Invalid role selected");
            populateRoles(model);
            return "auth/register";
        }

       try {
           employeeService.save(employee);

        } catch (DataIntegrityViolationException e){
            model.addAttribute("credentials", "такой Username уже занят");
            populateRoles(model);
            return "auth/register";
        }

        Cookie cookie = new Cookie("username", employee.getUsername());
        cookie.setMaxAge(60 * 60 * 24); // 1 день
        response.addCookie(cookie);

        return "redirect:/acc";
    }

    @GetMapping("/login")
    public String authentication(
            @RequestParam(value = "error", required = false) Boolean isError,
            @ModelAttribute("auth") Auth auth,
            Model model) {

        System.out.println(auth.getPass());
        if (Boolean.TRUE.equals(isError)) {
            model.addAttribute("credentials", "Неверный логин или пароль");
        }

        return "auth/loginPage";
    }

    @PostMapping("/login")
    public String authentication(Model model,
                                 HttpServletResponse response,
                                 @ModelAttribute("auth") Auth auth,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("error " + bindingResult.getAllErrors());
            return "auth/loginPage";
        }


        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPass());
        System.out.println(auth.getUsername() + " " + auth.getPass());


        try {
            authenticationManager.authenticate(authenticationToken);

        } catch (BadCredentialsException e){
            model.addAttribute("credentials", "Неверные учетные данные");
            //model.addAttribute("auth", auth);
            System.out.println("Не верные данные");
            return "auth/loginPage";
        }

        System.out.println("Аутентификация");
        Cookie cookie = new Cookie("username", auth.getUsername());
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        return "redirect:/acc";

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

    private void populateRoles(Model model) {
        List<Role> roles = roleRepo.findAll().stream()
                .filter(role -> !"ROLE_ADMIN".equals(role.getName()))
                .collect(Collectors.toList());
        model.addAttribute("roles", roles);
    }
}