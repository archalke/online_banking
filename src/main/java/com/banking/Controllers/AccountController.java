package com.banking.Controllers;

import com.banking.Repository.AccountRepository;
import com.banking.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller//indicates this class is controller
@RequestMapping(path = "/onlinebanking/accounts") //indicates url starts with /onlinebanking after application context
public class AccountController {
    //This means to get the bean data called account repository which is auto generated by spring, we use it to handle the data
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(path = "/addaccount") //maps only post requests
    public @ResponseBody
    String addNewAccount(@RequestParam Long customerid, @RequestParam Character accounttype) {
        //response body means the returned string is the response
        //request param means it is a parameter from the get or post request
        Account account = new Account();
        accountRepository.save(account);
        return "account added successfuly";
    }

    @GetMapping(path="/test")
    public @ResponseBody String test(){
        return "Hello  ";
    }

    //for single account - find details by account number
    @GetMapping(path="/{accountnumber}")
    public @ResponseBody Iterable<Account> getAccountDetails( @PathVariable("accountnumber") Long accountnumber ) {

        return accountRepository.findByAccountNumber(accountnumber);

    }

    @GetMapping(path="/all")
    public @ResponseBody List<Account> getAllAccounts() throws JsonProcessingException {
        //this returns json
        List<Account> allAccounts = new ArrayList<>();

        Iterable iterable = accountRepository.findAll();


        for (Object a: iterable ) {
                allAccounts.add( (Account) a);
        }

        return allAccounts;

//        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser(allAccounts);
//        allAccounts.addAll( accountRepository.findAll());
//        return jacksonJsonParser.toString();
//        return accountRepository.findAll();

    }

}