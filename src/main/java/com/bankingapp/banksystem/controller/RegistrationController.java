package com.bankingapp.banksystem.controller;

import com.bankingapp.banksystem.model.User;
import com.bankingapp.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showForm")
    public String showFormSignUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "sign-up";
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(@ModelAttribute("user") User user, Model model) {
        if (userService.checkExistsUserWithUsername(user.getUsername())) {
            model.addAttribute("usernameExists", true);
            return "sign-up";
        }
        userService.save(user);
        return "index";
    }
}
