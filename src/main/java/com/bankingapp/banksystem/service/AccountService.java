package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.factory.AccountFactory;
import com.bankingapp.banksystem.factory.TransactionFactory;
import com.bankingapp.banksystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class AccountService {

    private AccountFactory accountFactory;

    private TransactionFactory transactionFactory;

    @Autowired
    public AccountService(AccountFactory accountFactory, TransactionFactory transactionFactory) {
        this.accountFactory = accountFactory;
        this.transactionFactory = transactionFactory;
    }

    @Transactional
    public void makeDeposit(User user, String accountType, BigDecimal amount) {
        Account account = accountFactory.getAccount(user, accountType);
        deposit(account, amount);
        account.addTransaction(transactionFactory.getTransaction(accountType, "Deposit", "Account", amount, account.getAccountBalance()));
    }

    @Transactional
    public void makeWithdraw(User user, String accountType, BigDecimal amount) {
        Account account = accountFactory.getAccount(user, accountType);
        withdraw(account, amount);
        account.addTransaction(transactionFactory.getTransaction(accountType, "Withdraw", "Account", amount, account.getAccountBalance()));
    }

    private void deposit(Account account, BigDecimal amount) {
        account.setAccountBalance(account.getAccountBalance().add(amount));
    }

    private void withdraw(Account account, BigDecimal amount) {
        account.setAccountBalance(account.getAccountBalance().subtract(amount));
    }
}
