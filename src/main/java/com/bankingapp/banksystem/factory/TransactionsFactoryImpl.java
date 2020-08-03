package com.bankingapp.banksystem.factory;

import com.bankingapp.banksystem.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionsFactoryImpl implements TransactionsFactory{

    @Override
    public <T> List<T> getTransactions(User user, String type) {
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
