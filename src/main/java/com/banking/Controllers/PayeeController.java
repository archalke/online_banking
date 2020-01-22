package com.banking.Controllers;

import com.banking.Repository.PayeeRepository;
import com.banking.Repository.UserRepository;
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

@Controller
@RequestMapping(path = "/onlinebanking/transfer")
public class PayeeController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PayeeServiceImpl payeeService;

    @GetMapping("/payee")
    public String Payee(Model model,Principal principal) {
        User user = userService.findByUserName(principal.getName());
        List<Payee> payeeList = payeeService.findAllPayees(user);
        Payee payee = new Payee();
        System.out.println("Payee online baking ");
        model.addAttribute("payee",payee);
        model.addAttribute("recipientList",payeeList);
        return "payee";
    }

    @PostMapping("/payee/save")
    public String savePayee(Model model,@ModelAttribute("payee") Payee payee,Principal principal){

        System.out.println(" Under save payee");
        User user = userService.findByUserName( principal.getName() );

        boolean payeeNameExists = (payeeService.findPayeeByName(payee.getName())!=null);
        boolean payeeAccountExists = (payeeService.findByAccountNumber(payee.getAccountNumber())!=null);

        if(payeeNameExists && payeeAccountExists ) {
            List<Payee> payeeList = payeeService.findAllPayees(user);
            model.addAttribute("payee",payee);
            model.addAttribute("recipientList",payeeList);
            model.addAttribute("payeeNameExists",true);
            model.addAttribute("payeeAccountExists",true);
            return "payee";
        }
        if(payeeNameExists) {
            List<Payee> payeeList = payeeService.findAllPayees(user);
            model.addAttribute("payee",payee);
            model.addAttribute("recipientList",payeeList);
            model.addAttribute("payeeNameExists",true);
            return "payee";
        }
        if(payeeAccountExists) {
            List<Payee> payeeList = payeeService.findAllPayees(user);
            model.addAttribute("payee",payee);
            model.addAttribute("recipientList",payeeList);
            model.addAttribute("payeeAccountExists",true);
            return "payee";
        }

        payee.setUser(user);
        payeeService.addPayee(payee);
        List<Payee> payeeList = payeeService.findAllPayees(user);
        model.addAttribute("payee",new Payee());
        model.addAttribute("recipientList",payeeList);
        return "payee";

    }


    @PostMapping("/payee")
    public String PayeePost(@ModelAttribute("payee") Payee payee, Model model){
        System.out.println("Inside print post");
        return "";
    }

    @GetMapping("/payee/edit")
    public String editPayee(Model model, Principal principal, @RequestParam(value = "payeeName")String payeeName){

        User user = userService.findByUserName( principal.getName() );

        List<Payee> payees = payeeService.findAllPayees(user);
        Payee payee = payeeService.findPayeeByName(payeeName);

        model.addAttribute("payee",payee);
        model.addAttribute("recipientList",payees);

        return "payee";
    }

    @GetMapping("/payee/delete")
    public String deletePayee(Model model,@RequestParam(value = "payeeName")String payeeName, Principal principal){
        payeeService.removePayee( payeeService.findPayeeByName(payeeName) );
        User user = userService.findByUserName(principal.getName());
        List<Payee> payeeList = payeeService.findAllPayees(user);
        model.addAttribute("payee",new Payee());
        model.addAttribute("recipientList",payeeList);
        return "payee";

    }
}
