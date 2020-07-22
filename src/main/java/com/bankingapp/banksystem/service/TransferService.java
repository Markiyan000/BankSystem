package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferService {

    @Transactional
    public void transferBetweenAccounts(User user, String from, double amount) {
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        if (from.equals("primary")) {
            transfer(primaryAccount, savingsAccount, amount);
            primaryAccount.addTransaction(new PrimaryTransaction(LocalDateTime.now(), "Transfer To Savings Account", "Transfer", "Finished", amount, primaryAccount.getAccountBalance()));
        } else {
            transfer(savingsAccount, primaryAccount, amount);
            savingsAccount.addTransaction(new SavingsTransaction(LocalDateTime.now(), "Transfer To Primary Account", "Transfer", "Finished", amount, primaryAccount.getAccountBalance()));
        }
    }

    private void transfer(Account from, Account to, double amount) {
        from.setAccountBalance(from.getAccountBalance().subtract(new BigDecimal(amount)));
        to.setAccountBalance(to.getAccountBalance().add(new BigDecimal(amount)));
    }
}
