package com.banking.Service;

import com.banking.domain.Account;
import com.banking.domain.AccountType;
import com.banking.domain.Transaction;
import com.banking.domain.User;

import java.util.List;

public interface AccountService {

        User createAccounts( User user);
        void deposit(String accountType,String amount,User user);
        void withdraw(String accountType,String amount,User user);
        List<Transaction> transactionList(Account account);
        void save(Account account);

}
