package com.bankingapp.banksystem.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "primary_transaction")
public class PrimaryTransaction extends Transaction{

    @OneToOne
    @JoinColumn(name = "primary_account_id")
    private PrimaryAccount primaryAccount;

    public PrimaryTransaction() {

    }

    public PrimaryTransaction(LocalDateTime date, String description, String type, String status, Double amount, BigDecimal availableBalance) {
        super(date, description, type, status, amount, availableBalance);
    }

    public PrimaryAccount getPrimaryAccount() {
        return primaryAccount;
    }

    public void setPrimaryAccount(PrimaryAccount primaryAccount) {
        this.primaryAccount = primaryAccount;
    }
}
