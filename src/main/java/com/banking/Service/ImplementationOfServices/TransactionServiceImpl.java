package com.banking.Service.ImplementationOfServices;

import com.banking.Repository.TransactionRepository;
import com.banking.Service.TransactionService;
import com.banking.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void saveDepositTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void saveWithdrawTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

   @Override
    public void betweenAccountsTransfer() {

    }

    @Override
    public List<Transaction> findTransactions() {
        return transactionRepository.findAll();
    }


}
