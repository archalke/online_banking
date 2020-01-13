package com.banking.Service.ImplementationOfServices;

import com.banking.Repository.AccountRepository;
import com.banking.Service.AccountService;
import com.banking.domain.Account;
import com.banking.domain.AccountType;
import com.banking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public User createAccounts(User user) {

       //create both saving and checking for new user
        List<Account> accountList = new ArrayList<>();//user.getAccounts();

            Account account;
            long count = accountRepository.count();
            account = new Account();
            account.setAccountType(AccountType.C);
            account.setAccountNumber(count + 1);
            account.setEnrollDate(LocalDateTime.now());
            account.setUser(user);
            accountList.add(account);
            accountRepository.save(account);

            account = new Account();
            account.setAccountType(AccountType.S);
            account.setAccountNumber(count + 2);
            account.setEnrollDate(LocalDateTime.now());
            account.setUser(user);
            accountList.add(account);

            user.setAccounts(accountList);

            System.out.println( "Account List ");
            accountList.forEach(System.out::println);

        return user;
    }

    @Override
    public void deposit(String accountType, double amount) {

    }

    @Override
    public void withdraw(String accountType, double amount) {

    }
}
