package com.bankingapp.banksystem.controller.controller_view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/")
public class AccountViewController {

    @GetMapping("/showFormForDeposit")
    public String showFormForDeposit() {
        return "depositForm";
    }

    @GetMapping("/showFormForWithdraw")
    public String showFormForWithdraw() {
        return "withdrawForm";
    }

}
