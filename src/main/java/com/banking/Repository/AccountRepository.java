package com.banking.Repository;

import com.banking.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends CrudRepository<Account,Long>{

    Iterable<Account> findByAccountNumber(Long accountnumber);

}