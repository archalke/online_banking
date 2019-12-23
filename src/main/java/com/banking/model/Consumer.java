package com.banking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Consumer implements Serializable {

    @Id
    @GeneratedValue
    private Long Id;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long consumerId;
    private String firstName;
    private String lastName;
    private Address presentAddress;
    private Address mailingAddress;
    private Long[] accountNumbers;

    public Consumer(){}

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
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

    public Address getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(Address presentAddress) {
        this.presentAddress = presentAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Long[] getAccountNumbers() {
        return accountNumbers;
    }

    public void setAccountNumbers(Long[] accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

}
