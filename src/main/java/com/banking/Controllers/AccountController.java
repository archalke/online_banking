package com.banking.Controllers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.sift.AppenderFactoryUsingJoran;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.sift.AppenderFactory;
import com.banking.Service.AccountService;
import com.banking.Service.UserService;
import com.banking.domain.Account;
import com.banking.domain.AccountType;
import com.banking.domain.Transaction;
import com.banking.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/onlinebanking/account")
public class AccountController {

    public static Logger logger = (Logger) LoggerFactory.getLogger(AccountController.class);

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    AccountController(){
        logger.setLevel(Level.DEBUG);
    }

    @GetMapping("/primaryAccount")
    public String primaryAccount(Principal principal, Model model){

        User user = userService.findByUserName(principal.getName());
        Account primaryAccount = null;
        List<Transaction> primaryTransactionList = new ArrayList<>();

        if(user!=null && !(user.getAccounts().isEmpty())) {
            primaryAccount = user.getAccounts().get(AccountType.PRIMARY_ACCOUNT_INDEX);
            final Account finalPrimaryAccount = primaryAccount;
            primaryTransactionList = accountService.transactionList(finalPrimaryAccount);
        }

        model.addAttribute("primaryAccount",primaryAccount);
        model.addAttribute("primaryTransactionList",primaryTransactionList);
        return "primaryAccount";

    }


    @GetMapping("/savingsAccount")
    public String savingsAccount(Principal principal, Model model){

        logger.info("Fetch all savings accounts");

        User user = userService.findByUserName(principal.getName());
        Account savingsAccount = null;
        List<Transaction> savingsTransactionList = new ArrayList<>();

        if(user!=null && !(user.getAccounts().isEmpty())) {
            savingsAccount = user.getAccounts().get(AccountType.SAVINGS_ACCOUNT_INDEX);
            final Account finalSavingAccount = savingsAccount;
            System.out.println("finalSavingAccount "+ finalSavingAccount);
            savingsTransactionList = accountService.transactionList(finalSavingAccount);

            System.out.println("transaction  before adding to model");
            savingsTransactionList.forEach(transaction -> {System.out.println(transaction);} );
        }

        model.addAttribute("savingsAccount",savingsAccount);
        model.addAttribute("savingsTransactionList",savingsTransactionList);

        return "savingsAccount";

    }

    // deposit money
    @GetMapping("/deposit")
    public String deposit(Principal principal, Model model){
        model.addAttribute("accountType","");
        model.addAttribute("amount","");
        return "deposit";
    }


    @PostMapping("/deposit")
    public String depositPost(Model model,Principal principal, @ModelAttribute("accountType")String accountType, @ModelAttribute("amount")String amount){
        try {

            logger.debug("Entering deposit amount method");

            User user = userService.findByUserName(principal.getName());
            System.out.println(" ***********  " + accountType + amount);

            logger.info("Depositing amount " + amount + " to " + accountType + " accounts");

            if (Double.parseDouble(amount) < 1) {
                model.addAttribute("amount", "..Enter positive amount..");
                logger.warn(" User tried to deposit amount less than a dollar ");
                return "deposit";
            }

            accountService.deposit(accountType, amount, user);

        }catch (Exception e){
            logger.error("Error in depositing an amount  ");
            e.printStackTrace();
            return "generalError";
        }

        return "deposit";

    }


    @GetMapping("/withdraw")
    public String withdraw(Principal principal, Model model){
        model.addAttribute("accountType","");
        model.addAttribute("amount","");
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawPost(Model model,Principal principal, @ModelAttribute("accountType")String accountType, @ModelAttribute("amount")String amount){
        User user = userService.findByUserName(principal.getName());
        if(Double.valueOf(amount)<=0){
            model.addAttribute("amount","..Enter positive amount..");
            return "withdraw";
        }
        System.out.println( " ***********  "+ accountType + amount);
        accountService.withdraw(accountType,amount,user);
        model.addAttribute("statusValue","");
        return "withdraw";
    }


}
