package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.utils.StringUtils;
import com.bankingapp.banksystem.factory.AccountFactory;
import com.bankingapp.banksystem.factory.TransactionFactory;
import com.bankingapp.banksystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferService {

    private AccountFactory accountFactory;

    private TransactionFactory transactionFactory;

    @Autowired
    public TransferService(AccountFactory accountFactory, TransactionFactory transactionFactory) {
        this.accountFactory = accountFactory;
        this.transactionFactory = transactionFactory;
    }

    @Transactional
    public void transferBetweenAccounts(User user, String from, double amount) {
        Account sender = accountFactory.getAccount(user, from);
        Account receiver = accountFactory.getAccount(user, StringUtils.changeAccountType(from));

        sender.addTransaction(transactionFactory.getTransaction(from, "Transfer", "Transfer", amount, sender.getAccountBalance()));

        transfer(sender, receiver, amount);
    }

    @Transactional
    public void transferToReceiver(User sender, User receiver, double amount) {
        PrimaryAccount senderAccount = sender.getPrimaryAccount();
        PrimaryAccount receiverAccount = receiver.getPrimaryAccount();

        senderAccount.addTransaction(new PrimaryTransaction(LocalDateTime.now(), "Transfer To " + receiver.getUsername(), "Transfer", "Finished", amount, senderAccount.getAccountBalance()));
        receiverAccount.addTransaction(new PrimaryTransaction(LocalDateTime.now(), "Received From " + sender.getUsername(), "Transfer", "Finished", amount, receiverAccount.getAccountBalance()));

        transfer(senderAccount, receiverAccount, amount);
    }

    private void transfer(Account from, Account to, double amount) {
        from.setAccountBalance(from.getAccountBalance().subtract(new BigDecimal(amount)));
        to.setAccountBalance(to.getAccountBalance().add(new BigDecimal(amount)));
    }
}
