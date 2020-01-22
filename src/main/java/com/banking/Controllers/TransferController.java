package com.banking.Controllers;

import com.banking.Repository.AccountRepository;
import com.banking.Service.AccountService;
import com.banking.Service.PayeeService;
import com.banking.Service.TransactionService;
import com.banking.Service.UserService;
import com.banking.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(path = "/onlinebanking/transfer")
public class TransferController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    PayeeService payeeService;


    @GetMapping("/betweenAccounts")
    public String betweenAccountsGet(Model model){
        model.addAttribute("transferFrom","");
        model.addAttribute("transferTo","");
        model.addAttribute("amount","");
        return "betweenAccounts";
    }


    @PostMapping("/betweenAccounts")
    public String betweenAccountsPost(Principal principal, @ModelAttribute("transferFrom")String transferFrom, @ModelAttribute("transferTo")String transferTo,Model model,@ModelAttribute("amount")String amount){

        // **** Note : Only two conditions -- if transferring from primary or if transferring from saving
        User user = userService.findByUserName( principal.getName());
        Account primaryAccount = user.getAccounts().get(AccountType.PRIMARY_ACCOUNT_INDEX);
        Account savingsAccount = user.getAccounts().get(AccountType.SAVINGS_ACCOUNT_INDEX);
        BigDecimal amountToTransfer = new BigDecimal(amount);
        Transaction transactionFrom = null;
        Transaction transactionTo = null;

        if( transferFrom.toUpperCase().equals("PRIMARY") && primaryAccount.getAccountBalance().compareTo(amountToTransfer) >= 0 ){
            BigDecimal newSavingsAccountBalance = savingsAccount.getAccountBalance().add(amountToTransfer);
            savingsAccount.setAccountBalance(newSavingsAccountBalance);
            primaryAccount.setAccountBalance( primaryAccount.getAccountBalance().subtract(amountToTransfer));
            transactionFrom = new Transaction("Transferred to Saving Account","Completed",amountToTransfer,primaryAccount,"Account");
            transactionTo   = new Transaction("Transferred from Primary Account","Completed",amountToTransfer,savingsAccount,"Account");
        }

        if(transferFrom.toUpperCase().equals("SAVING") && savingsAccount.getAccountBalance().compareTo(amountToTransfer) >= 0 ){
            BigDecimal newPrimaryAccountBalance = primaryAccount.getAccountBalance().add(amountToTransfer);
            primaryAccount.setAccountBalance(newPrimaryAccountBalance);
            savingsAccount.setAccountBalance( savingsAccount.getAccountBalance().subtract(amountToTransfer));
            transactionFrom = new Transaction("Transferred from Saving Account","Completed",amountToTransfer,savingsAccount,"Account");
            transactionTo   = new Transaction("Transferred to Primary Account","Completed",amountToTransfer,primaryAccount,"Account");
        }

        accountService.save(savingsAccount);
        accountService.save(primaryAccount);

        transactionService.saveBetweenAccountsTransfer(transactionFrom,transactionTo);

        return "betweenAccounts";

    }

    @GetMapping("/toOtherPersonsAccount")
    public String toOtherPersonsAccountGet(Model model, Principal principal){
        User user = userService.findByUserName( principal.getName() );
        List<Payee> payeeList = payeeService.findAllPayees(user);
        model.addAttribute("recipientName","");
        model.addAttribute("accountType","");
        model.addAttribute("recipientList",payeeList);
        model.addAttribute("amount","");
        return "toOtherPersonsAccount";
    }

    @PostMapping("/toOtherPersonsAccount")
    public String toOtherPersonsAccountPost(@ModelAttribute("recipientName")String recipientName, Principal principal,Model model,@ModelAttribute("accountType")String accountType,@ModelAttribute("amount")String amount) {

        // **** Note : Only two conditions -- if transferring from primary or if transferring from saving
        User user = userService.findByUserName( principal.getName());
        Account primaryAccount = user.getAccounts().get(AccountType.PRIMARY_ACCOUNT_INDEX);
        Account savingsAccount = user.getAccounts().get(AccountType.SAVINGS_ACCOUNT_INDEX);
        BigDecimal amountToTransfer = new BigDecimal(amount);
        Transaction transactionFrom = null;

        if( accountType.toUpperCase().equals("PRIMARY") && primaryAccount.getAccountBalance().compareTo(amountToTransfer) >= 0 ){
            primaryAccount.setAccountBalance( primaryAccount.getAccountBalance().subtract(amountToTransfer));
            transactionFrom = new Transaction("Transferred to "+recipientName,"Completed",amountToTransfer,primaryAccount,"Transfer");
            accountService.save(primaryAccount);
        }

        if(accountType.toUpperCase().equals("SAVING") && savingsAccount.getAccountBalance().compareTo(amountToTransfer) >= 0 ){
            savingsAccount.setAccountBalance( savingsAccount.getAccountBalance().subtract(amountToTransfer));
            transactionFrom = new Transaction("Transferred to "+recipientName ,"Completed",amountToTransfer,savingsAccount,"Account");
            accountService.save(savingsAccount);
        }

        transactionService.saveOtherTransfers(transactionFrom);

        return "toOtherPersonsAccount";
    }

    }
