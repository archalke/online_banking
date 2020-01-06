package com.banking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


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
    @JsonIgnore
    private Consumer consumer;

    private LocalDateTime enrollDate;// = LocalDateTime.now();
    private Character ebillStatus='P';
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private BigDecimal accountBalance ;


    public Account(){
        accountBalance = new BigDecimal("0.00");
    }

    @Override
    public String toString() {
        return "Account details  "+ "Account Number : "+accountNumber+"\n"+
                                    "Account Status : "+accountStatus+"\n"+
                                    "Customer  :    "+consumer+"\n";
    }

    public Long getAccountNumber() {

        String acc = ("0000000000"+accountNumber);
        int startIndex = acc.length()-10;
        System.out.println( "Inside set Account Number --    "+ accountNumber);
        this.accountNumber = Long.valueOf(acc.substring(startIndex));
        return accountNumber;

    }

    public void setAccountNumber(Long accountNumber) {

        String acc = ("0000000000"+accountNumber);
        int startIndex = acc.length()-10;
        System.out.println( "Inside set Account Number --    "+ accountNumber);
        this.accountNumber = Long.valueOf(acc.substring(startIndex));

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

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

}
