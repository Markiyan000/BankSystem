package com.bankingapp.banksystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "savings_account")
public class SavingsAccount extends Account{

    @OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL)
    private List<SavingsTransaction> savingsTransactions;

    public List<SavingsTransaction> getSavingsTransactions() {
        return savingsTransactions;
    }

    public void setSavingsTransactions(List<SavingsTransaction> savingsTransactions) {
        this.savingsTransactions = savingsTransactions;
    }
}
