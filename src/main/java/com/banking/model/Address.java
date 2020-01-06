package com.banking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties(value = {"consumer"})
public class Address implements Serializable {

    @Id
    @GeneratedValue
    private Long Id;

    private String street1;
    private String street2;
    private String city;
    private String state;
    private String country;
    private int zip;
    @Enumerated(value = EnumType.STRING)
    private AddressType addressType;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    public Address(){}

    public Address( String street1, String street2, String city, String state, String country, int zip,AddressType addressType) {
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.addressType = addressType;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}
