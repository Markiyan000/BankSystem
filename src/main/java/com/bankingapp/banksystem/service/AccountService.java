package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.model.PrimaryAccount;
import com.bankingapp.banksystem.model.SavingsAccount;
import com.bankingapp.banksystem.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class AccountService {

    @Transactional
    public void makeDeposit(User user, String accountType, Double amount) {
        if (accountType.equalsIgnoreCase("primary")) {
            makePrimaryAccountDeposit(user, amount);
        } else {
            makeSavingsAccountDeposit(user, amount);
        }
    }

    @Transactional
    public void makeWithdraw(User user, String accountType, Double amount) {
        if(accountType.equalsIgnoreCase("primary")) {
            makePrimaryAccountWithdraw(user, amount);
        } else {
            makeSavingsAccountWithdraw(user, amount);
        }
    }

    private void makePrimaryAccountDeposit(User user, Double amount) {
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
    }

    private void makeSavingsAccountDeposit(User user, Double amount) {
        SavingsAccount savingsAccount = user.getSavingsAccount();
        savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
    }

    private void makePrimaryAccountWithdraw(User user, Double amount) {
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
    }

    private void makeSavingsAccountWithdraw(User user, Double amount) {
        SavingsAccount savingsAccount = user.getSavingsAccount();
        savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
    }
}
