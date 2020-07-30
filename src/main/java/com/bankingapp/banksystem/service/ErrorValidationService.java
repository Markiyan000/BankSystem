package com.bankingapp.banksystem.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ErrorValidationService {

    public void checkEmptyString(String string, Model model) {
        if (string.isEmpty()) {
            model.addAttribute("empty", true);
        }
    }

}
