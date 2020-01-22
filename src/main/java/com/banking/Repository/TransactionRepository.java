package com.banking.Repository;

import com.banking.domain.Account;
import com.banking.domain.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {

        List<Transaction> findAll();

}
