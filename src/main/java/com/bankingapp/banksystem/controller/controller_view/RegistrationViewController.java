package com.bankingapp.banksystem.controller.controller_view;

import com.bankingapp.banksystem.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationViewController {

    @GetMapping("/showForm")
    public String showFormSignUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "sign-up";
    }
}
