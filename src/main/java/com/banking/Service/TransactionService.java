package com.banking.Service;

import com.banking.model.Transaction;

import java.util.List;

public interface TransactionService {

    void saveDepositTransaction( Transaction transaction );
    void saveWithdrawTransaction( Transaction transaction );
    void betweenAccountsTransfer(  );
    List<Transaction> findTransactions();

}