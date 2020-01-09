package com.banking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties(value = {"user"})
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
    @JoinColumn(name = "user_id")
    private User user;


}
