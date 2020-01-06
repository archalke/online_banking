package com.banking.Service.ImplementationOfServices;

import com.banking.Repository.AccountRepository;
import com.banking.Repository.ConsumerRepository;
import com.banking.Service.AccountService;
import com.banking.Service.ConsumerService;
import com.banking.model.Account;
import com.banking.model.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    AccountService accountService;

    @Override
    public Consumer findByUserName(String userName) {
        return consumerRepository.findByUserName(userName);
    }

    @Override
    public Consumer findByEmail(String email) {
        return consumerRepository.findByEmail(email);
    }

    @Override
    public boolean checkUserExists(String userName, String email) {

        if(checkUserNameExists(userName)||checkEmailExists(email)) {
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean checkUserNameExists(String userName) {
        return (null!=consumerRepository.findByUserName(userName));
    }

    @Override
    public boolean checkEmailExists(String email) {
        return (null!=consumerRepository.findByEmail(email));
    }

    @Override
    public void save(Consumer consumer) {

         List<Account> accounts = consumer.getAccounts();
        Long count = consumerRepository.count();

        System.out.println( "Consumer Count :  "+consumerRepository.count());

        Consumer savedConsumer =  consumerRepository.save(consumer);

        if(!accounts.isEmpty()){
            accountService.createAccount(accounts.get(0),"S",savedConsumer);
            accountService.createAccount(accounts.get(1),"C",savedConsumer);
        }

    }


    @Override
    public List<Consumer> findAllConsumers() {
        List<Consumer> consumers = new ArrayList<Consumer>();
        consumerRepository.findAll().forEach(consumers::add);
        return consumers;
    }

    @Override
    public void activateUser(String userName) {
        Consumer consumer = consumerRepository.findByUserName(userName);
        consumer.setActivate(true);
        consumerRepository.save(consumer);
    }

    @Override
    public void deactivateUser(String userName) {
        Consumer consumer = consumerRepository.findByUserName(userName);
        consumer.setActivate(false);
        consumerRepository.save(consumer);
    }

}
