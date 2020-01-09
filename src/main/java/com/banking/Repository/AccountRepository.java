package com.banking.Repository;

import com.banking.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends CrudRepository<Account,Long>{

    List<Account> findByAccountNumber(Long accountnumber);

}