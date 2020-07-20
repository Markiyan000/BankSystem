package com.bankingapp.banksystem.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "savings_transaction")
public class SavingsTransaction extends Transaction{

    @OneToOne
    @JoinColumn(name = "savings_account_id")
    private SavingsAccount savingsAccount;

    public SavingsTransaction() {
    }

    public SavingsTransaction(LocalDateTime date, String description, String type, String status, Double amount, BigDecimal availableBalance) {
        super(date, description, type, status, amount, availableBalance);
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }
}
