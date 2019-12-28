package com.banking.Repository;

import com.banking.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends CrudRepository<Account,Long>{

    Iterable<Account> findByAccountNumber(Long accountnumber);

//    List<Account> findByConsumerId(Long consumerId);

//    List<Account> findByConsumer_id(Long id);



}