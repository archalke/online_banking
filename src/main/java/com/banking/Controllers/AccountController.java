package com.banking.Controllers;

import com.banking.Exceptions.UserException;
import com.banking.Log.LogWrapper;
import com.banking.Service.AccountService;
import com.banking.Service.UserService;
import com.banking.domain.Account;
import com.banking.domain.AccountType;
import com.banking.domain.Transaction;
import com.banking.domain.User;
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

    public static LogWrapper log = LogWrapper.getLogger(AccountController.class);

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    AccountController(){

    }

    @GetMapping("/primaryAccount")
    public String primaryAccount(Principal principal, Model model) {

        try {
            log.debug(" Getting primary account ");

            User user = userService.findByUserName(principal.getName());
            if(user!=null) {
                Account primaryAccount = null;
                List<Transaction> primaryTransactionList = new ArrayList<>();

                if (!user.getAccounts().isEmpty()) {
                    primaryAccount = user.getAccounts().get(AccountType.PRIMARY_ACCOUNT_INDEX);
                    final Account finalPrimaryAccount = primaryAccount;
                    primaryTransactionList = accountService.transactionList(finalPrimaryAccount);
                    log.debug("Get the primary account of user : " + primaryAccount.toString() + " with transaction size " + primaryTransactionList.size());
                }

                model.addAttribute("primaryAccount", primaryAccount);
                model.addAttribute("primaryTransactionList", primaryTransactionList);

                if (primaryAccount != null)
                    log.info(" Successfully fetched the primary account ");
            }else {
                throw new UserException("User is not available while fetching primary account details");
            }
        }catch (Exception e){
            log.error("Unable to fetch account details for user:  "+principal.getName());
            e.printStackTrace();
            return "generalError";
        }
        return "primaryAccount";
    }


    @GetMapping("/savingsAccount")
    public String savingsAccount(Principal principal, Model model){

        log.info(" Fetching saving account ");
        try {

            User user = userService.findByUserName(principal.getName());

            if(user!=null) {
                Account savingsAccount = null;
                List<Transaction> savingsTransactionList = new ArrayList<>();

                if (user != null && !(user.getAccounts().isEmpty())) {
                    savingsAccount = user.getAccounts().get(AccountType.SAVINGS_ACCOUNT_INDEX);
                    final Account finalSavingAccount = savingsAccount;
                    System.out.println("finalSavingAccount " + finalSavingAccount);
                    savingsTransactionList = accountService.transactionList(finalSavingAccount);
                    log.debug("Get the savings account of user : " + savingsAccount.toString() + " with transaction count : " + savingsTransactionList.size());
                }

                if (savingsAccount != null)
                    log.info(" Successfully fetched the Saving account ");

                model.addAttribute("savingsAccount", savingsAccount);
                model.addAttribute("savingsTransactionList", savingsTransactionList);

            }else {
                throw new UserException("User is not available while fetching savings account details");
            }

            log.info(" Successfully fetched the saving account details");

        }catch(Exception e){
            log.error("Unable to fetch account details for user:  "+principal.getName());
            e.printStackTrace();
            return "generalError";
        }

        return "savingsAccount";

    }

    // deposit money
    @GetMapping("/deposit")
    public String deposit(Principal principal, Model model){

        log.debug("user clicked - deposit option");
        model.addAttribute("accountType","");
        model.addAttribute("amount","");
        return "deposit";

    }


    @PostMapping("/deposit")
    public String depositPost(Model model,Principal principal, @ModelAttribute("accountType")String accountType, @ModelAttribute("amount")String amount){

        try {

            User user = userService.findByUserName(principal.getName());
            if(user!=null) {

                log.info("user initiated deposit");

                if (Double.parseDouble(amount) < 1) {
                    model.addAttribute("amount", "..Enter positive amount..");
                    log.info(" User tried to deposit amount less than a dollar ");
                    return "deposit";
                }
                accountService.deposit(accountType, amount, user);

                log.info(" Amount deposited successfully for user "+ user.getUsername() + " of amount " +  amount );
                model.addAttribute("accountType","");
                model.addAttribute("amount","");

            }else{
                throw new Exception("User Not Found - while depositing an amount");
            }

        }catch (Exception e){
            log.error("Error while depositing an amount for user: "+ principal.getName());
            e.printStackTrace();
            return "generalError";
        }

        return "deposit";

    }


    @GetMapping("/withdraw")
    public String withdraw(Principal principal, Model model){
        log.debug("user clicked - withdraw option");
        model.addAttribute("accountType","");
        model.addAttribute("amount","");
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawPost(Model model,Principal principal, @ModelAttribute("accountType")String accountType, @ModelAttribute("amount")String amount){

        try{
            log.info("user initiated withdraw");
            User user = userService.findByUserName(principal.getName());
            if(user!=null) {
                log.debug("Withdrawing amount " + amount + " from " + accountType + " accounts");

                if (Double.parseDouble(amount) <= 0) {
                    model.addAttribute("amount", "..Enter positive amount..");
                    log.warn("User entered negative amount so exiting");
                    return "withdraw";
                }

                accountService.withdraw(accountType, amount, user);
                model.addAttribute("statusValue", "");
                model.addAttribute("accountType","");
                model.addAttribute("amount","");
            }else{
                throw new UserException("User not found - while withdrawing an amount");
            }
        }catch(Exception e){
            log.error("Error while withdrawing : ");
            e.printStackTrace();
            return "generalError";
        }
        log.info("user successfully completed withdrawal");
        return "withdraw";
    }


}
