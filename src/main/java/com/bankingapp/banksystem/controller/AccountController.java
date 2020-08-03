package com.bankingapp.banksystem.controller;

import com.bankingapp.banksystem.factory.AccountFactory;
import com.bankingapp.banksystem.factory.TransactionsFactory;
import com.bankingapp.banksystem.factory.TransactionsFactoryImpl;
import com.bankingapp.banksystem.model.*;
import com.bankingapp.banksystem.service.AccountService;
import com.bankingapp.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private UserService userService;

    private AccountService accountService;

    private AccountFactory accountFactory;

    private TransactionsFactory transactionsFactory;

    @Autowired
    public AccountController(UserService userService, AccountService accountService, AccountFactory accountFactory, TransactionsFactory transactionsFactory) {
        this.userService = userService;
        this.accountService = accountService;
        this.accountFactory = accountFactory;
        this.transactionsFactory = transactionsFactory;
    }

    @GetMapping("/{type}")
    public String account(@PathVariable String type, Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        Account account = accountFactory.getAccount(user, type);
        List<Transaction> transactions = transactionsFactory.getTransactions(user, type);
        model.addAttribute("account", account);
        model.addAttribute("transactions", transactions);

        return "account";
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
