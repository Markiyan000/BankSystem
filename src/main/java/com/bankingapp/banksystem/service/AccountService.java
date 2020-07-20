package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    @Transactional
    public void makeDeposit(User user, String accountType, Double amount) {
        if (accountType.equalsIgnoreCase("primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            deposit(primaryAccount, amount);
            primaryAccount.addTransaction(new PrimaryTransaction(LocalDateTime.now(), "Deposit To Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance()));
        } else {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            deposit(savingsAccount, amount);
            savingsAccount.addTransaction(new SavingsTransaction(LocalDateTime.now(), "Deposit To Savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance()));
        }
    }

    @Transactional
    public void makeWithdraw(User user, String accountType, Double amount) {
        if (accountType.equalsIgnoreCase("primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            withdraw(primaryAccount, amount);
            primaryAccount.addTransaction(new PrimaryTransaction(LocalDateTime.now(), "Withdraw From Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance()));
        } else {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            withdraw(savingsAccount, amount);
            savingsAccount.addTransaction(new SavingsTransaction(LocalDateTime.now(), "Withdraw From Savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance()));
        }
    }

    private void deposit(Account account, double amount) {
        account.setAccountBalance(account.getAccountBalance().add(new BigDecimal(amount)));
    }

    private void withdraw(Account account, double amount) {
        account.setAccountBalance(account.getAccountBalance().subtract(new BigDecimal(amount)));
    }
}
