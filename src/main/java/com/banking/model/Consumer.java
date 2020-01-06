package com.banking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Consumer implements Serializable {

    @Id
    @GeneratedValue
    private Long Id;
    @NotBlank(message = "Username is mandatory")
    private String userName;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @OneToMany( mappedBy = "consumer", cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<Address> Addresses;

    @OneToMany( mappedBy = "consumer" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> Accounts;

    private String email;
    private String phone;
    private boolean activate=true;
    @JsonIgnore
    private String password;


    public Consumer(){

        Accounts = new ArrayList<Account>();
        Accounts.add( new Account());
        Accounts.add( new Account());

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActivate() {      return activate;    }

    public void setActivate(boolean activate) {       this.activate = activate;    }

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

    public List<Address> getAddresses() {
        return Addresses;
    }

    public void setAddresses(List<Address> Addresses) {
        this.Addresses = Addresses;
    }

    public List<Account> getAccounts() {
        return Accounts;
    }

    public void setAccounts(List<Account> accounts) {
        Accounts = accounts;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "Id=" + Id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Addresses=" + Addresses +
                ", Accounts=" + Accounts +
                '}';
    }


}
