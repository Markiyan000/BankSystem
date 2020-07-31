package com.bankingapp.banksystem.factory;

import com.bankingapp.banksystem.model.Account;
import com.bankingapp.banksystem.model.User;

public class AccountFactory {

    public static Account getAccount(User user, String type) {
        switch (type) {
            case "primary": {
                return user.getPrimaryAccount();
            }
            case "savings": {
                return user.getSavingsAccount();
            }
        }

        return null;
    }
}
