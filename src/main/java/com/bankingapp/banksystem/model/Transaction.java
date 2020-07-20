package com.bankingapp.banksystem.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "available_balance")
    private BigDecimal availableBalance;

    public Transaction() {

    }

    public Transaction(LocalDateTime date, String description, String type, String status, Double amount, BigDecimal availableBalance) {
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

}
