package com.banking.Service.ImplementationOfServices;

import com.banking.Repository.PayeeRepository;
import com.banking.Service.PayeeService;
import com.banking.domain.Payee;
import com.banking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PayeeServiceImpl implements PayeeService {

    @Autowired
    PayeeRepository payeeRepository;

    @Override
    public void addPayee(Payee payee) {
        payeeRepository.save(payee);
    }

    @Override
    public List<Payee> findAllPayees(User user) {
        return payeeRepository.findAll().stream().filter(payee -> payee.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public void removePayee(Payee payee) {
        payeeRepository.delete(payee);
    }

    @Override
    public Payee findPayeeByName(String name) {
        return payeeRepository.findByName(name);
    }

    public Payee findByAccountNumber(Long accountNumber) {
        return payeeRepository.findByAccountNumber(accountNumber);
    }

}
