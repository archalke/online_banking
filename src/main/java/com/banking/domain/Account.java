package com.banking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long accountNumber;
    private Character accountStatus = 'A';

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private LocalDateTime enrollDate;// = LocalDateTime.now();
    private Character ebillStatus='P';
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private BigDecimal accountBalance = new BigDecimal("0.00");


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

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountStatus=" + accountStatus +
                ", enrollDate=" + enrollDate +
                ", ebillStatus=" + ebillStatus +
                ", accountType=" + accountType +
                ", accountBalance=" + accountBalance +
                '}';
    }

}
