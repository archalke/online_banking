package com.banking.model;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Consumer implements Serializable {

    @Id
    @GeneratedValue
    private Long Id;
    private String userName;
    private String firstName;
    private String lastName;
    @OneToMany( mappedBy = "consumer", cascade = CascadeType.ALL )
    private List<Address> presentAddress;
    @OneToMany( mappedBy = "consumer" , fetch = FetchType.LAZY)
    private List<Account> Accounts;

    public Consumer(){}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(List<Address> presentAddress) {
        this.presentAddress = presentAddress;
    }

    public List<Account> getAccounts() {
        return Accounts;
    }

    public void setAccounts(List<Account> accounts) {
        Accounts = accounts;
    }



}
