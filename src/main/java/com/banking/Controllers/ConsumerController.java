package com.banking.Controllers;

import com.banking.Repository.AccountRepository;
import com.banking.Repository.AddressRepository;
import com.banking.Repository.ConsumerRepository;
import com.banking.model.Account;
import com.banking.model.Address;
import com.banking.model.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller//indicates this class is controller
@RequestMapping(path="/onlinebanking/consumer")//indicates URL starts with onlinebanking after application context
public class ConsumerController {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;


    @GetMapping(path="/{userName}")
    public @ResponseBody Consumer getConsumerDetails(@PathVariable("userName") String userName ) {

        Consumer consumer =  consumerRepository.findByUserName(userName);

        System.out.println( "Consumer Id : "+ consumer.getId() );
        System.out.println( "Consumer User Name : "+ consumer.getUserName() );
        System.out.println( "Consumer first name : "+ consumer.getFirstName() );
        System.out.println( "Consumer last name : "+ consumer.getLastName() );

        System.out.println( "Consumer Accounts exist : "+ ( consumer.getAccounts()!=null ) );
        System.out.println( "Consumer Addresses exist : "+ ( consumer.getAddresses()!=null ) );


//        List<Account> accountList = accountRepository.findByConsumer_id(consumer.getId());
//        List<Address> addressList = addressRepository.findByConsumer_id(consumer.getId());

        return consumer;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Consumer create(@RequestBody Consumer consumer){
        return consumerRepository.save(consumer);
    }




}
