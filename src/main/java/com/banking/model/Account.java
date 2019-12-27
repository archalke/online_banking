package com.banking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long accountNumber;
    private Character accountStatus = 'A';
    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;
    private LocalDateTime enrollDate = LocalDateTime.now();
    private Character ebillStatus;
    private Character accountType;
    private Double accountBalance = 0.00;


    public Account(){}

    @Override
    public String toString() {
        return "Account details  "+ "Account Number : "+accountNumber+"\n"+
                                    "Account Status : "+accountStatus+"\n"+
                                    "Customer  :    "+consumer+"\n";
    }


}
