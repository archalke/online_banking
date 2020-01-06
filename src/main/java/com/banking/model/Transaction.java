package com.banking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private LocalDateTime transactionInitiationTime;
    private String description;
    private String transactionType;
    private String status;
    private double amount;
    private BigDecimal availableBalance;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(){}

    public Transaction(Long id, LocalDateTime transactionInitiationTime, String description, String transactionType, String status, double amount, BigDecimal availableBalanceAfterTransaction, Account account) {
        Id = id;
        this.transactionInitiationTime = transactionInitiationTime;
        this.description = description;
        this.transactionType = transactionType;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalanceAfterTransaction;
        this.account = account;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDateTime getTransactionInitiationTime() {
        return transactionInitiationTime;
    }

    public void setTransactionInitiationTime(LocalDateTime transactionInitiationTime) {
        this.transactionInitiationTime = transactionInitiationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BigDecimal getAvailableBalanceAfterTransaction() {
        return availableBalance;
    }

    public void setAvailableBalanceAfterTransaction(BigDecimal availableBalanceAfterTransaction) {
        this.availableBalance = availableBalanceAfterTransaction;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }
}
