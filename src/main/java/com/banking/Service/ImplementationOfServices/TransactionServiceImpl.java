package com.banking.Service.ImplementationOfServices;

import com.banking.Repository.TransactionRepository;
import com.banking.Service.TransactionService;
import com.banking.domain.Account;
import com.banking.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
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
    public void saveBetweenAccountsTransfer(Transaction transactionFrom,Transaction transactionTo) {
        transactionRepository.save(transactionFrom);
        transactionRepository.save(transactionTo);
    }

    @Override
    public void saveOtherTransfers(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findTransactions() {
        return transactionRepository.findAll();
    }


}
