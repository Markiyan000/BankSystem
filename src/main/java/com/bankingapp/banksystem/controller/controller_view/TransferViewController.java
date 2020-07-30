package com.bankingapp.banksystem.controller.controller_view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfer")
public class TransferViewController {

    @GetMapping("/showTransferBetweenAccounts")
    public String showFormForTransferBetweenAccounts() {
        return "transferBetweenAccounts";
    }

    @GetMapping("/showTransferToReceiver")
    public String showFormTransferBetweenAccounts() {
        return "transferToReceiver";
    }
}
