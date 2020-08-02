package com.bankingapp.banksystem.factory;

import com.bankingapp.banksystem.model.User;

import java.util.List;

public class TransactionsFactory {

    public static <T> List<T> getTransactions(User user, String type) {
        switch (type) {
            case "primary": {
                return (List<T>) user.getPrimaryAccount().getPrimaryTransactions();
            }
            case "savings": {
                return (List<T>) user.getSavingsAccount().getSavingsTransactions();
            }
        }

        return null;
    }
}
