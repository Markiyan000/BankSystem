package com.bankingapp.banksystem.controller;

import com.bankingapp.banksystem.model.User;
import com.bankingapp.banksystem.service.TransferService;
import com.bankingapp.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    private UserService userService;

    private TransferService transferService;

    @Autowired
    public TransferController(UserService userService, TransferService transferService) {
        this.userService = userService;
        this.transferService = transferService;
    }

    @GetMapping("/betweenAccounts/{from}/{amount}")
    public String transferBetweenAccounts(Principal principal,
                                          @PathVariable String from,
                                          @PathVariable String amount) {
        User user = userService.findByUsername(principal.getName());
        transferService.transferBetweenAccounts(user, from, Double.parseDouble(amount));

        return "redirect:/userFront";
    }

}
