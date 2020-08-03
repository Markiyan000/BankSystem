package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.utils.StringUtils;
import com.bankingapp.banksystem.factory.AccountFactory;
import com.bankingapp.banksystem.factory.TransactionFactory;
import com.bankingapp.banksystem.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferService {

    @Transactional
    public void transferBetweenAccounts(User user, String from, double amount) {
        Account sender = AccountFactory.getAccount(user, from);
        Account receiver = AccountFactory.getAccount(user, StringUtils.changeAccountType(from));

        sender.addTransaction(TransactionFactory.getTransaction(from, "Transfer", "Transfer", amount, sender.getAccountBalance()));

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
