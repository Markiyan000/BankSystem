package com.bankingapp.banksystem.controller;

import com.bankingapp.banksystem.model.Loan;
import com.bankingapp.banksystem.model.User;
import com.bankingapp.banksystem.service.LoanService;
import com.bankingapp.banksystem.service.TransferService;
import com.bankingapp.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequestMapping("/loan")
public class LoanController {

    private UserService userService;

    private LoanService loanService;

    private TransferService transferService;

    @Autowired
    public LoanController(UserService userService, LoanService loanService, TransferService transferService) {
        this.userService = userService;
        this.loanService = loanService;
        this.transferService = transferService;
    }

    @GetMapping
    public String createLoan(@ModelAttribute Loan loan, Principal principal) {
        User receiver = userService.findByUsername(loan.getReceiver());

        loanService.addLoan(receiver, loan);

        return "redirect:/userFront";
    }

    @GetMapping("/submit")
    public String submitLoan(@RequestParam String borrowerName, @RequestParam String amount, Principal principal) {
        User borrower = userService.findByUsername(borrowerName);
        User receiver = userService.findByUsername(principal.getName());

        transferService.transferToReceiver(receiver, borrower, Double.parseDouble(amount));

        return "redirect:/userFront";
    }
}
