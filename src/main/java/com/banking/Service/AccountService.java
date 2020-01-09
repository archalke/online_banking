package com.banking.Service;

import com.banking.model.Account;
import com.banking.model.User;

public interface AccountService {

        Account createAccount(Account account, String accountType, User user);
        void deposit(String accountType,double amount);
        void withdraw(String accountType,double amount);

}
