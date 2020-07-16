package com.bankingapp.banksystem.model;

import javax.persistence.*;

@Entity
@Table(name = "savings_transaction")
public class SavingsTransaction extends Transaction{

    @OneToOne
    @JoinColumn(name = "savings_account_id")
    private SavingsAccount savingsAccount;

}
