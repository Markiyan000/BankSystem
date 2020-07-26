package com.bankingapp.banksystem.controller;

import com.bankingapp.banksystem.model.*;
import com.bankingapp.banksystem.service.AccountService;
import com.bankingapp.banksystem.service.ErrorValidationService;
import com.bankingapp.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private UserService userService;

    private AccountService accountService;
    @Autowired
    public AccountController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/primary")
    public String primaryAccount(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        List<PrimaryTransaction> primaryTransactions = primaryAccount.getPrimaryTransactions();

        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("primaryTransactions", primaryTransactions);

        return "primaryAccount";
    }

    @GetMapping("/savings")
    public String savingsAccount(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        SavingsAccount savingsAccount = user.getSavingsAccount();
        List<SavingsTransaction> savingsTransactions = savingsAccount.getSavingsTransactions();

        model.addAttribute("savingsAccount", savingsAccount);
        model.addAttribute("savingsTransactions", savingsTransactions);

        return "savingsAccount";
    }

    @GetMapping("/showFormForDeposit")
    public String showFormForDeposit() {
        return "depositForm";
    }

    @GetMapping("/showFormForWithdraw")
    public String showFormForWithdraw() {
        return "withdrawForm";
    }

    @GetMapping("/deposit")
    public String makeDeposit(@RequestParam String accountType, @RequestParam String amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        accountService.makeDeposit(user, accountType, Double.parseDouble(amount));
        return "redirect:/userFront";
    }

    @GetMapping("/withdraw")
    public String makeWithDraw(@RequestParam String accountType, @RequestParam String amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        accountService.makeWithdraw(user, accountType, Double.parseDouble(amount));
        return "redirect:/userFront";
    }
}
