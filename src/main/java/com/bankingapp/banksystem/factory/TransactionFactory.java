package com.bankingapp.banksystem.factory;

import com.bankingapp.banksystem.model.Transaction;

import java.math.BigDecimal;

public interface TransactionFactory {
    Transaction getTransaction(String accountType, String actionType, String obj, BigDecimal amount, BigDecimal balance);
}
