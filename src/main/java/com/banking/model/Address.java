package com.banking.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue
    private Long Id;
    private Long addressId;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String country;
    private int zip;
    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;


    public Address( Long addressId, String street1, String street2, String city, String state, String country, int zip) {

        this.addressId = addressId;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }


}
