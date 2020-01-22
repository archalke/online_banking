package com.banking.Repository;

import com.banking.domain.Payee;
import com.banking.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PayeeRepository extends CrudRepository<Payee, Long> {

    List<Payee> findAll();
    Payee findByName(String payeeName);
    List<Payee> findByUser(User user);
    Payee findByAccountNumber(Long accountNumber);
}
