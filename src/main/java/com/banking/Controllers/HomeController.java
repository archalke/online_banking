package com.banking.Controllers;

import com.banking.Repository.RoleRepository;
import com.banking.Service.UserService;
import com.banking.domain.Account;
import com.banking.domain.AccountType;
import com.banking.domain.User;
import com.banking.domain.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller

public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public String Home(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        System.out.println("Inside /index ");
        return "index";
    }

    @GetMapping("/signup")
    public String signUpGet(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpPost(@ModelAttribute("user") User user, Model model){

        System.out.println( user );

        if(userService.checkUserExists(user.getUsername(),user.getEmail())){

            if(userService.checkUserNameExists(user.getUsername())){
                model.addAttribute("usernameExists",true);
            }
            if(userService.checkEmailExists(user.getEmail())){
                model.addAttribute("emailExists",true);
            }
            return "signup";

        }else{
            Set<UserRole> userRoles = new HashSet<>();
            userService.createUser(user, userRoles);
            return "index";
        }

    }

    @RequestMapping("/onlinebanking/homePage")
    public String homePage(Principal principal, Model model){

        User user = userService.findByUserName(principal.getName());

        Account primaryAccount = user.getAccounts().get(AccountType.PRIMARY_ACCOUNT_INDEX);
        Account savingsAccount = user.getAccounts().get(AccountType.SAVINGS_ACCOUNT_INDEX);

        model.addAttribute("primaryAccount",primaryAccount);
        model.addAttribute("savingsAccount",savingsAccount);

        return "homePage";

    }

}
