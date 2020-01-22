package com.banking.Service;

import com.banking.domain.Payee;
import com.banking.domain.User;

import java.util.List;

public interface PayeeService {

    void addPayee(Payee payee);
    List<Payee> findAllPayees(User user);
    void removePayee(Payee payee);
    Payee findPayeeByName(String name);
    Payee findByAccountNumber(Long accountNumber);

}
