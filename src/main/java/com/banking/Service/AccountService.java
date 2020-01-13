package com.banking.Service;

import com.banking.domain.Account;
import com.banking.domain.User;

public interface AccountService {

        User createAccounts( User user);
        void deposit(String accountType,double amount);
        void withdraw(String accountType,double amount);

}
