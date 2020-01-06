package com.banking.Repository;

import com.banking.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {

        List<Transaction> findAll();

}
