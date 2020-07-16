package com.bankingapp.banksystem.model;

import javax.persistence.*;

@Entity
@Table(name = "primary_transaction")
public class PrimaryTransaction extends Transaction{

    @OneToOne
    @JoinColumn(name = "primary_account_id")
    private PrimaryAccount primaryAccount;

    public PrimaryAccount getPrimaryAccount() {
        return primaryAccount;
    }

    public void setPrimaryAccount(PrimaryAccount primaryAccount) {
        this.primaryAccount = primaryAccount;
    }
}
