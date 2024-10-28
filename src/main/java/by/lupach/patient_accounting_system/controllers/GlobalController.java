package by.lupach.patient_accounting_system.controllers;

import by.lupach.patient_accounting_system.entities.User;
import by.lupach.patient_accounting_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class GlobalController {

    @Autowired
    private UserService userService; // Сервис для работы с вашим классом User

    @ModelAttribute
    public void addUserToModel(Model model, @Autowired User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            model.addAttribute("user", user);
        }
    }
}
