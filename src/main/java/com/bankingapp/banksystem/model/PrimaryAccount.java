package com.bankingapp.banksystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "primary_account")
public class PrimaryAccount extends Account {

    @OneToMany(mappedBy = "primaryAccount", cascade = CascadeType.ALL)
    private List<PrimaryTransaction> primaryTransactions;

    public List<PrimaryTransaction> getPrimaryTransactions() {
        return primaryTransactions;
    }

    public void setPrimaryTransactions(List<PrimaryTransaction> primaryTransactions) {
        this.primaryTransactions = primaryTransactions;
    }
}
