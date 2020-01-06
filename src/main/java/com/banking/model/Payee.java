package com.banking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class Payee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private String email;
    private String phone;
    private String accountNumber;
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="consumer_id")
    private Consumer consumer;

    public Payee() {
    }

    public Payee(Long id, String name, String email, String phone, String accountNumber, String description, Consumer consumer) {
        Id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.accountNumber = accountNumber;
        this.description = description;
        this.consumer = consumer;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

}
