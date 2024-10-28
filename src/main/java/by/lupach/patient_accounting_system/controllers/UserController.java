package by.lupach.patient_accounting_system.controllers;

import by.lupach.patient_accounting_system.entities.User;
import by.lupach.patient_accounting_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("signup")
    public String processSignup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login_processing")
    public String processLogin(String username, String password) {
        userService.loadUserByUsername(username);
        return "redirect:/";
    }


}
