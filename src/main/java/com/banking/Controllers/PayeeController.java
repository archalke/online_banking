package com.banking.Controllers;

import com.banking.config.PageNames;
import com.banking.config.StandardMessages;
import com.banking.Exceptions.UserException;
import com.banking.Log.LogWrapper;
import com.banking.Service.ImplementationOfServices.PayeeServiceImpl;
import com.banking.Service.ImplementationOfServices.UserServiceImpl;
import com.banking.domain.Payee;
import com.banking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path = "/onlinebanking/transfer")
public class PayeeController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PayeeServiceImpl payeeService;

    public static final LogWrapper log = LogWrapper.getLogger(PayeeController.class);

    @GetMapping("/payee")
    public String Payee(Model model,Principal principal) {

        log.debug("Load all payees");
        try{

            User user = userService.findByUserName(principal.getName());
            if(user!=null) {
                List<Payee> payeeList = payeeService.findAllPayees(user);
                Payee payee = new Payee();
                model.addAttribute("payee", payee);
                model.addAttribute("recipientList", payeeList);
            }
        }catch (Exception e){
            log.error("Not able to payees");
            e.printStackTrace();
        }
        return "payee";

    }

    @PostMapping("/payee/save")
    public String savePayee(Model model,@ModelAttribute("payee") Payee payee,Principal principal){

        log.debug("Save payee");

        try {

            User user = userService.findByUserName(principal.getName());

            if(user!=null) {

                List<Payee> payees = payeeService.findAllPayees(user);

                payees.forEach(System.out::println);

                boolean payeeNameExistsUnderUser = payees.stream().anyMatch(payee1 -> payee1.getName().equals(payee.getName()));
                boolean payeeAccountExistsUnderUser = payees.stream().anyMatch(payee1 -> payee1.getAccountNumber().equals(payee.getAccountNumber())) ;

                if(payeeAccountExistsUnderUser || payeeNameExistsUnderUser){
                    model.addAttribute("payeeExists",true);
                    model.addAttribute("payee", payee);
                    model.addAttribute("recipientList", payees);
                    return "payee";
                }

                boolean userEmailExists = userService.findByEmail(payee.getEmail())!=null;
                boolean userAccountExists =  userService.findByAccountNumber(payee.getAccountNumber())!=null;

                if(!userEmailExists || !userAccountExists){
                    model.addAttribute("noAccountExists",true);
                    model.addAttribute("payee", payee);
                    model.addAttribute("recipientList", payees);
                    return "payee";
                }

//                if (userEmailExists && userAccountExists) {
//                    List<Payee> payeeList = payeeService.findAllPayees(user);
//                    model.addAttribute("payee", payee);
//                    model.addAttribute("recipientList", payeeList);
//                    model.addAttribute("payeeNameExists", true);
//                    model.addAttribute("payeeAccountExists", true);
//                    log.info("payee name and account already exists");
//                    return "payee";
//                }
//
//                if (userEmailExists) {
//                    List<Payee> payeeList = payeeService.findAllPayees(user);
//                    model.addAttribute("payee", payee);
//                    model.addAttribute("recipientList", payeeList);
//                    model.addAttribute("payeeNameExists", true);
//                    log.info("payee name  already exists");
//                    return "payee";
//                }
//
//                if (userAccountExists) {
//                    List<Payee> payeeList = payeeService.findAllPayees(user);
//                    model.addAttribute("payee", payee);
//                    model.addAttribute("recipientList", payeeList);
//                    model.addAttribute("payeeAccountExists", true);
//                    log.info("payee  account already exists");
//                    return "payee";
//                }

                payee.setUser(user);
                payeeService.addPayee(payee);
                List<Payee> payeeList = payeeService.findAllPayees(user);
                model.addAttribute("payee", new Payee());
                model.addAttribute("recipientList", payeeList);
                log.debug("Successfully saved payee");

            }else{
                throw new UserException(StandardMessages.userLoadIssue);
            }

        }catch (Exception e){
            log.error(StandardMessages.unknownError);
            e.printStackTrace();
            return PageNames.errorPage;
        }

        return "payee";

    }


    @PostMapping("/payee")
    public String PayeePost(@ModelAttribute("payee") Payee payee, Model model){
        log.debug("Payee Post");
        return "";
    }

    @GetMapping("/payee/edit")
    public String editPayee(Model model, Principal principal, @RequestParam(value = "payeeName")String payeeName){
        log.debug("Edit a payee");
        try {
            User user = userService.findByUserName(principal.getName());
            if(user!=null) {
                List<Payee> payees = payeeService.findAllPayees(user);
                Payee payee = payeeService.findPayeeByName(payeeName);

                model.addAttribute("payee", payee);
                model.addAttribute("recipientList", payees);
            }else{
                throw new UserException(StandardMessages.userLoadIssue);
            }
        }catch (Exception e){
            log.error(StandardMessages.unknownError);
            e.printStackTrace();
            return PageNames.errorPage;
        }
        return "payee";
    }

    @GetMapping("/payee/delete")
    public String deletePayee(Model model,@RequestParam(value = "payeeName")String payeeName, Principal principal){
        log.debug("Remove payee");
        payeeService.removePayee( payeeService.findPayeeByName(payeeName) );
        User user = userService.findByUserName(principal.getName());
        List<Payee> payeeList = payeeService.findAllPayees(user);
        model.addAttribute("payee",new Payee());
        model.addAttribute("recipientList",payeeList);
        log.debug(StandardMessages.payeeDeleted);
        return "payee";
    }
}
