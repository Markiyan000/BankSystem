package com.bankingapp.banksystem.factory;

import com.bankingapp.banksystem.model.Account;
import com.bankingapp.banksystem.model.User;

public class AccountFactory {

    public static Account getAccount(User user, String type) {
        return type.equals("primary") ? user.getPrimaryAccount() : user.getSavingsAccount();
    }
}
