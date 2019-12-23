package com.banking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long accountNumber;
    private Character accountStatus = 'A';
    @Column(nullable = false)
    private Long consumerId;
    private LocalDateTime enrollDate = LocalDateTime.now();
    private Character ebillStatus;
    private Character accountType;
    private Double accountBalance = 0.00;

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }
//    private String[] billers;

    public Account(){}

    public Account(long consumerId, char accountType){
        this.consumerId = consumerId;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account details  "+ "Account Number : "+accountNumber+"\n"+
                                    "Account Status : "+accountStatus+"\n"+
                                    "Customer Id :    "+consumerId+"\n";
    }

    public Long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Character getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(char accountStatus) {
        this.accountStatus = accountStatus;
    }



    public LocalDateTime getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDateTime enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Character getEbillStatus() {
        return ebillStatus;
    }

    public void setEbillStatus(char ebillStatus) {
        this.ebillStatus = ebillStatus;
    }

    public Character getAccountType() {
        return accountType;
    }

    public void setAccountType(char accountType) {
        this.accountType = accountType;
    }

}
