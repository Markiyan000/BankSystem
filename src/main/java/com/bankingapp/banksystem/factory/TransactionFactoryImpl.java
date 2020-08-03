package com.bankingapp.banksystem.factory;

import com.bankingapp.banksystem.model.PrimaryTransaction;
import com.bankingapp.banksystem.model.SavingsTransaction;
import com.bankingapp.banksystem.model.Transaction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class TransactionFactoryImpl implements TransactionFactory {

    @Override
    public Transaction getTransaction(String accountType, String actionType, String obj, Double amount, BigDecimal balance) {

        StringBuilder description = new StringBuilder().append(actionType).append(" ").append(StringUtils.capitalize(accountType)).append(" Account");

        switch (accountType) {
            case "primary": {
                return new PrimaryTransaction(LocalDateTime.now(), description.toString(), obj, "Finished", amount, balance);
            }
            case "savings": {
                return new SavingsTransaction(LocalDateTime.now(), description.toString(), obj, "Finished", amount, balance);
            }
        }
        return null;
    }
}
