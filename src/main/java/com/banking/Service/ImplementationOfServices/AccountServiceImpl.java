package com.banking.Service.ImplementationOfServices;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.banking.Controllers.AccountController;
import com.banking.Exceptions.UserException;
import com.banking.Log.LogWrapper;
import com.banking.Repository.AccountRepository;
import com.banking.Service.AccountService;
import com.banking.Service.TransactionService;
import com.banking.domain.Account;
import com.banking.domain.AccountType;
import com.banking.domain.Transaction;
import com.banking.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionService transactionService;

    public static LogWrapper log = LogWrapper.getLogger(AccountServiceImpl.class);




    @Override
    public User createAccounts(User user) {

        log.debug("Creating New accounts for user");

        try {
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
            log.info("Successfully created accounts for user " + user.getUsername());

        }catch (Exception e){
            log.error("Account creation operation interrupted for user "+ (user!=null ? user.getUsername():""));
            e.printStackTrace();
            throw e;
        }
        return user;
    }

    @Override
    public List<Transaction> transactionList(Account account) {

        //reverse order -- so latest transaction should be on top
//        Collection<Transaction> transactionCollection = transactionService.findTransactions().stream().filter(transaction -> transaction.getLinkedToAccount().equals(account)).sorted((o1, o2) -> o2.getTransactionDateTime().compareTo(o1.getTransactionDateTime())).collect(Collectors.toList());
        List<Transaction> transactions = transactionService.findTransactions().stream().filter(transaction -> transaction.getLinkedToAccount().equals(account)).sorted((o1, o2) -> o2.getTransactionDateTime().compareTo(o1.getTransactionDateTime())).collect(Collectors.toList());
        log.debug("Retrieved all required transaction and its size is "+transactions.size());
        return transactions;
    }

    @Override
    public void deposit(String accountType, String amount,User user) {

        log.debug("deposit operation started");

        int accountTypeIndex =  accountType.equals("Primary")? 0:1;
        Account depositAccount = user.getAccounts().get(accountTypeIndex);
        BigDecimal amountToAdd = new BigDecimal(amount);
        depositAccount.setAccountBalance( depositAccount.getAccountBalance().add(amountToAdd));
        depositAccount = accountRepository.save(depositAccount);

        //Update this deposit as transaction
        String depositMessage = "deposited to "+depositAccount.getAccountType().getType()+" Account";
        Transaction transaction = new Transaction(depositMessage,"Completed",amountToAdd,depositAccount,"Account");
        transactionService.saveDepositTransaction(transaction);

    }

    @Override
    public void withdraw(String accountType, String amount,User user) {

        log.debug("withdraw operation started");

        int accountTypeIndex = accountType.equals("Primary") ? 0 : 1;
        Account withdrawAccount = user.getAccounts().get(accountTypeIndex);
        BigDecimal currentAccountBalance = withdrawAccount.getAccountBalance();
        BigDecimal amountToSubtract = new BigDecimal(amount);

       if( currentAccountBalance.compareTo(amountToSubtract)>=0){
            BigDecimal newAccountBalance = currentAccountBalance.subtract( amountToSubtract );
            withdrawAccount.setAccountBalance(newAccountBalance);
            withdrawAccount = accountRepository.save(withdrawAccount);

           //Update this withdraw as transaction
           String withdrawMessage = "Withdraw from "+withdrawAccount.getAccountType().getType()+" Account";
           Transaction transaction = new Transaction(withdrawMessage,"Completed",amountToSubtract,withdrawAccount,"Account");
           transactionService.saveDepositTransaction(transaction);

       }else{
            System.out.println("inside else under withdraw");
       }

    }

    @Override
    public void save(Account account) {
        log.debug("Save account");
        accountRepository.save(account);
    }

    @Override
    public Account findByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
