package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long accountNumber;
    private Character accountStatus;
    private Long customerId;
    private Date enrollDate;
    private Character ebillStatus;
    private Character accountType;
//    private String[] billers;

    public Account(){}

    public Account(long customerId, char accountType){
        this.customerId = customerId;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account details  "+ "Account Number : "+accountNumber+"\n"+
                                    "Account Status : "+accountStatus+"\n"+
                                    "Customer Id :    "+customerId+"\n";
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
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

//    public String[] getBillers() {
//        return billers;
//    }
//
//    public void setBillers(String[] billers) {
//        this.billers = billers;
//    }

}
