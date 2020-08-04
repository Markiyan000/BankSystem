package com.bankingapp.banksystem.controller;

import com.bankingapp.banksystem.model.Loan;
import com.bankingapp.banksystem.model.User;
import com.bankingapp.banksystem.service.LoanService;
import com.bankingapp.banksystem.service.TransferService;
import com.bankingapp.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String createLoan(@ModelAttribute Loan loan, Principal principal) {
        loan.setBorrowerName(principal.getName());
        User receiver = userService.findByUsername(loan.getReceiverName());

        loanService.addLoan(receiver, loan);

        return "userFront";
    }

    @GetMapping("/submit/{loanId}")
    public String submitLoan(@PathVariable Long loanId, Principal principal) {

        Loan loan = loanService.findById(loanId);

        User borrower = userService.findByUsername(loan.getBorrowerName());
        User receiver = userService.findByUsername(principal.getName());

        transferService.transferToReceiver(receiver, borrower, loan.getAmount());
        loanService.removeById(loanId);

        return "redirect:/userFront";
    }

    @DeleteMapping("/reject/{loanId}")
    public String rejectLoan(@PathVariable Long loanId) {
        loanService.removeById(loanId);

        return "redirect:/userFront";
    }
}
