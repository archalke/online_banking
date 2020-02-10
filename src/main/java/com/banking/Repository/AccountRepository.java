package com.banking.Repository;

import com.banking.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends CrudRepository<Account,Long>{
    Account findByAccountNumber(Long accountnumber);
}