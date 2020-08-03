package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.model.Loan;
import com.bankingapp.banksystem.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService {

    @Transactional
    public void addLoan(User user, Loan loan) {
        user.addLoan(loan);
    }

}
