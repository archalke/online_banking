package com.banking.Service.ImplementationOfServices;

import com.banking.Log.LogWrapper;
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

    public static final LogWrapper log = LogWrapper.getLogger(PayeeServiceImpl.class);

    @Override
    public void addPayee(Payee payee) {
        log.debug("add payee");
        payeeRepository.save(payee);
    }

    @Override
    public List<Payee> findAllPayees(User user) {
        log.debug("find all payees");
        return payeeRepository.findAll().stream().filter(payee -> payee.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public void removePayee(Payee payee) {
        log.debug("Remove payee");
        payeeRepository.delete(payee);
    }

    @Override
    public Payee findPayeeByName(String name) {
        log.debug("find payee by name");
        return payeeRepository.findByName(name);
    }

    public Payee findByAccountNumber(Long accountNumber) {
        log.debug("find payee by account number");
        return payeeRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Payee findByEmail(String email) {
        log.debug("find payee by email");
        return payeeRepository.findByEmail(email);
    }

}
