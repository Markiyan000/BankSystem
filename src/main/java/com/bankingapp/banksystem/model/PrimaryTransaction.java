package com.bankingapp.banksystem.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "primary_transaction")
public class PrimaryTransaction extends Transaction{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_account_id")
    private PrimaryAccount primaryAccount;

    public PrimaryTransaction() {

    }

    public PrimaryTransaction(LocalDateTime date, String description, String type, String status, BigDecimal amount, BigDecimal availableBalance) {
        super(date, description, type, status, amount, availableBalance);
    }

    public Account getPrimaryAccount() {
        return primaryAccount;
    }

    public void setAccount(Account primaryAccount) {
        this.primaryAccount = (PrimaryAccount) primaryAccount;
    }
}
