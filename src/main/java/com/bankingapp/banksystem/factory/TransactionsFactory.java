package com.bankingapp.banksystem.factory;

import com.bankingapp.banksystem.model.User;

import java.util.List;

public interface TransactionsFactory {
    <T> List<T> getTransactions(User user, String type);
}
