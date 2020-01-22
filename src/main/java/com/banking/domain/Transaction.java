package com.banking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private LocalDateTime transactionDateTime;
    private String description;
    private String status;
    private BigDecimal amount;
    private BigDecimal availableBalance;
    private String transactionType;// Account or transfer

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account linkedToAccount;

    public Transaction(String description, String status, BigDecimal amount, Account linkedToAccount, String transactionType){
        this.transactionDateTime = LocalDateTime.now();
        this.description = description;
        this.status = status;
        this.amount = amount;
        this.availableBalance = linkedToAccount.getAccountBalance();
        this.linkedToAccount = linkedToAccount;
        this.transactionType = transactionType;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "Id=" + Id +
                ", transactionDateTime=" + transactionDateTime +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", availableBalance=" + availableBalance +
                ", transactionType='" + transactionType + '\'' +
                ", linkedToAccount=" + linkedToAccount +
                '}';
    }
}
