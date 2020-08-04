package com.bankingapp.banksystem.controller.controller_view;

import com.bankingapp.banksystem.model.Loan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loan")
public class LoanViewController {

    @GetMapping("/showLoanForm")
    public String showLoanForm(Model model) {
        Loan loan = new Loan();
        model.addAttribute("loan", loan);

        return "loanForm";
    }

}
