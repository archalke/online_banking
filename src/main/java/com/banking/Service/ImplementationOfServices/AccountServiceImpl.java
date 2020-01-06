package com.banking.Service.ImplementationOfServices;

import com.banking.Repository.AccountRepository;
import com.banking.Service.AccountService;
import com.banking.model.Account;
import com.banking.model.AccountType;
import com.banking.model.Consumer;
import jdk.internal.math.FormattedFloatingDecimal;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account, String accountType, Consumer consumer) {

        if(accountType.charAt(0)=='C'){
            account.setAccountType(AccountType.C);
            account.setAccountNumber( accountRepository.count()+1);
            account.setEnrollDate(LocalDateTime.now());
            account.setConsumer(consumer);
        }else{
            account.setAccountType(AccountType.S);
            account.setAccountNumber( accountRepository.count()+2);
            account.setEnrollDate(LocalDateTime.now());
            account.setConsumer(consumer);
        }

        return account;

    }

    @Override
    public void deposit(String accountType, double amount) {

    }

    @Override
    public void withdraw(String accountType, double amount) {

    }
}
