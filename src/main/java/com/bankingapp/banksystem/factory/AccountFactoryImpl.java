package com.bankingapp.banksystem.factory;

import com.bankingapp.banksystem.model.Account;
import com.bankingapp.banksystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class AccountFactoryImpl implements AccountFactory{

    @Override
    public Account getAccount(User user, String type) {
        return type.equals("primary") ? user.getPrimaryAccount() : user.getSavingsAccount();
    }
}
