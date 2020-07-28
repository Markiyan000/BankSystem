package com.bankingapp.banksystem.controller;

import com.bankingapp.banksystem.dao.RoleDao;
import com.bankingapp.banksystem.model.User;
import com.bankingapp.banksystem.model.security.UserRole;
import com.bankingapp.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {

    private final UserService userService;

    private final RoleDao roleDao;

    @Autowired
    public RegistrationController(UserService userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(@ModelAttribute("user") User user, Model model) {
        if (userService.checkExistsUserWithUsername(user.getUsername())) {
            model.addAttribute("usernameExists", true);
            return "sign-up";
        }
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
        userService.save(user, roles);
        return "index";
    }
}
