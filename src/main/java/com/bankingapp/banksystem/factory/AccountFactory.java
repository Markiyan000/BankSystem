package com.bankingapp.banksystem.factory;

import com.bankingapp.banksystem.model.Account;
import com.bankingapp.banksystem.model.User;

public interface AccountFactory {
    Account getAccount(User user, String accountType);
}
