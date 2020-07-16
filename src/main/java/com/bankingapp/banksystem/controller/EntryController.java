package com.bankingapp.banksystem.controller;

import com.bankingapp.banksystem.model.User;
import com.bankingapp.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class EntryController {

    private UserService userService;

    @Autowired
    public EntryController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userFront")
    public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("primaryAccount", user.getPrimaryAccount());
        model.addAttribute("savingsAccount", user.getSavingsAccount());

        return "userFront";
    }
}
