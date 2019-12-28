package com.banking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

enum AccountType{
    S("SAVING"), C("CHECKING");
    //declaring private variables for getting values
    private String type;
    //get method
    public String getType(){
        return this.type;
    }

    //enum constructor - can't be public or protected
    AccountType(String type){
        this.type = type;
    }
}

@Entity
@JsonIgnoreProperties(value = {"consumer"})

public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long accountNumber;
    private Character accountStatus = 'A';

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
//    @JsonIgnore
    private Consumer consumer;

    private LocalDateTime enrollDate;// = LocalDateTime.now();
    private Character ebillStatus;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Double accountBalance = 0.00;


    public Account(){}

    @Override
    public String toString() {
        return "Account details  "+ "Account Number : "+accountNumber+"\n"+
                                    "Account Status : "+accountStatus+"\n"+
                                    "Customer  :    "+consumer+"\n";
    }


//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Character getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Character accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
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

    public void setEbillStatus(Character ebillStatus) {
        this.ebillStatus = ebillStatus;
    }

    public String getAccountType() {
        return accountType.getType();
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

}
