package com.banking.Service;

import com.banking.model.Account;
import com.banking.model.AccountType;
import com.banking.model.Consumer;

public interface AccountService {

        Account createAccount(Account account, String accountType, Consumer consumer);
        void deposit(String accountType,double amount);
        void withdraw(String accountType,double amount);

}
