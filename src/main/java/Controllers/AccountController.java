package Controllers;

import model.Account;
import Repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller//indicates this class is controller
@RequestMapping(path = "/accounts") //indicates url starts with /accounts (after application)
public class AccountController {
    //This means to get the bean data called account repository which is auto generated by spring, we use it to handle the data
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(path = "/addaccount") //maps only post requests
    public @ResponseBody
    String addNewAccount(@RequestParam Long customerid, @RequestParam Character accounttype) {
        //response body means the returned string is the response
        //request param means it is a parameter from the get or post request
        Account account = new Account(customerid, accounttype);
        accountRepository.save(account);
        return "account added successfuly";
    }

    @GetMapping(path="/test")
    public @ResponseBody String test(){
        return "Hello  ";
    }

    //for single account
    @GetMapping(path="/oneaccount")
    public @ResponseBody Iterable<Account> getAllAccounts2() {
        return accountRepository.findAll();
    }

        @GetMapping(path="/all")
    public @ResponseBody String getAllAccounts() throws JsonProcessingException {
        //this returns json
        List<Account> allAccounts = new ArrayList<>();

        Iterable iterable = accountRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();

        for (Object a: iterable ) {
                allAccounts.add( (Account) a);
                Account account = objectMapper.readValue(((Account)a).toString(),Account.class);
                System.out.println(account.toString());
        }

        return "hello test";

//        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser(allAccounts);
//        allAccounts.addAll( accountRepository.findAll());
//        return jacksonJsonParser.toString();
//        return accountRepository.findAll();

    }

}