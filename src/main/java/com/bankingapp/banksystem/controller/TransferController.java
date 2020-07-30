package com.bankingapp.banksystem.controller;

import com.bankingapp.banksystem.model.User;
import com.bankingapp.banksystem.service.TransferService;
import com.bankingapp.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/betweenAccounts")
    public String transferBetweenAccounts(Principal principal,
                                          @RequestParam String accountType,
                                          @RequestParam String amount) {
        User user = userService.findByUsername(principal.getName());
        transferService.transferBetweenAccounts(user, accountType, Double.parseDouble(amount));

        return "redirect:/userFront";
    }

    @GetMapping("/toReceiver")
    public String transferToReceiver(Principal principal, @RequestParam String receiverName,
                                     @RequestParam String amount, Model model) {
        User sender = userService.findByUsername(principal.getName());
        User receiver = userService.findByUsername(receiverName);

        if (receiver == null) {
            model.addAttribute("userNotExists", true);
            return "transferToReceiver";
        }

        transferService.transferToReceiver(sender, receiver, Double.parseDouble(amount));

        return "redirect:/userFront";
    }
}
