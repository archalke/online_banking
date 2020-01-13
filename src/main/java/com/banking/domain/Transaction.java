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
    private LocalDateTime transactionInitiationTime;
    private String description;
    private String transactionAccountType;
    private String status;
    private double amount;
    private BigDecimal availableBalance;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account fromAccount;

}
