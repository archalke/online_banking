package com.banking.Repository;

import com.banking.domain.Payee;
import com.banking.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayeeRepository extends CrudRepository<Payee, Long> {

    List<Payee> findAll();
    Payee findByName(String payeeName);
    List<Payee> findByUser(User user);
    Payee findByAccountNumber(Long accountNumber);
    Payee findByEmail(String email);
}
