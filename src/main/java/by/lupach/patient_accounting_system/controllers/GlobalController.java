package by.lupach.patient_accounting_system.controllers;

import by.lupach.patient_accounting_system.entities.User;
import by.lupach.patient_accounting_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public void addUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails user = ((UserDetails) authentication.getPrincipal());
            User currentUser = userService.loadUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
            System.out.println(currentUser.getUsername());
            model.addAttribute("currentUser", currentUser);
        }
    }
}
