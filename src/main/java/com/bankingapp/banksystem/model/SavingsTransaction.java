package com.bankingapp.banksystem.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "savings_transaction")
public class SavingsTransaction extends Transaction{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "savings_account_id")
    private SavingsAccount savingsAccount;

    public SavingsTransaction() {
    }

    public SavingsTransaction(LocalDateTime date, String description, String type, String status, Double amount, BigDecimal availableBalance) {
        super(date, description, type, status, amount, availableBalance);
    }

    public Account getAccount() {
        return savingsAccount;
    }

    public void setAccount(Account savingsAccount) {
        this.savingsAccount = (SavingsAccount) savingsAccount;
    }
}
