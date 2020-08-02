package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.factory.AccountFactory;
import com.bankingapp.banksystem.factory.TransactionFactory;
import com.bankingapp.banksystem.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class AccountService {

    @Transactional
    public void makeDeposit(User user, String accountType, Double amount) {
        Account account = AccountFactory.getAccount(user, accountType);
        deposit(account, amount);
        account.addTransaction(TransactionFactory.getTransaction(accountType, "Deposit", "Account", amount, account.getAccountBalance()));
    }

    @Transactional
    public void makeWithdraw(User user, String accountType, Double amount) {
        Account account = AccountFactory.getAccount(user, accountType);
        withdraw(account, amount);
        account.addTransaction(TransactionFactory.getTransaction(accountType, "Withdraw", "Account", amount, account.getAccountBalance()));
    }

    private void deposit(Account account, double amount) {
        account.setAccountBalance(account.getAccountBalance().add(new BigDecimal(amount)));
    }

    private void withdraw(Account account, double amount) {
        account.setAccountBalance(account.getAccountBalance().subtract(new BigDecimal(amount)));
    }
}
