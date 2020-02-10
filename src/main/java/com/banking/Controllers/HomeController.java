package com.banking.Controllers;

import com.banking.Exceptions.UserException;
import com.banking.Log.LogWrapper;
//import com.banking.Repository.RoleRepository;
//import com.banking.Repository.UserRoleRepository;
import com.banking.Service.UserService;
import com.banking.config.PageNames;
import com.banking.domain.Account;
import com.banking.domain.AccountType;
import com.banking.domain.User;
import com.banking.domain.security.Role;
import com.banking.domain.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.jvm.hotspot.debugger.Page;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller

public class HomeController {

    @Autowired
    UserService userService;

//    @Autowired
//    RoleRepository roleRepository;

    public static LogWrapper log = LogWrapper.getLogger(HomeController.class);

    @GetMapping("/")
    public String Home(){
        log.info("Loading home page");
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        log.info("inside info");
        return "index";
    }

    @GetMapping("/signup")
    public String signUpGet(Model model){
        log.info("Sign up get accessed");
        User user = new User();
        model.addAttribute("user",user);
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpPost(@ModelAttribute("user") User user, Model model){

        log.info("Signup page");
        try {
            if (userService.checkUserExists(user.getUsername(), user.getEmail())) {

                if (userService.checkUserNameExists(user.getUsername())) {
                    model.addAttribute("usernameExists", true);
                    log.info("User already exists " + user.getUsername());
                }
                if (userService.checkEmailExists(user.getEmail())) {
                    model.addAttribute("emailExists", true);
                    log.info("User email exists " + user.getEmail());
                }
                return "signup";

            } else {
                log.info("sign up process started, will create new user and respective accounts");
                Set<UserRole> userRoles = new HashSet<>();
//                Role role = roleRepository.findByName("ROLE_USER");
//                UserRole uRole = new UserRole(user, role);
//                userRoles.add(uRole);

                userService.createUser(user, userRoles);
                log.info("successfully added new user and respective role");

                return "index";
            }
        }catch (Exception e){
            log.error("Error occurred");
            e.printStackTrace();
            return PageNames.errorPage;
        }
    }

    @RequestMapping("/onlinebanking/homePage")
    public String homePage(Principal principal, Model model){

        try {

            log.debug(" Landed to home page");
            User user = userService.findByUserName(principal.getName());
            if(user!=null) {
                Account primaryAccount = user.getAccounts().get(AccountType.PRIMARY_ACCOUNT_INDEX);
                Account savingsAccount = user.getAccounts().get(AccountType.SAVINGS_ACCOUNT_INDEX);
                model.addAttribute("primaryAccount", primaryAccount);
                model.addAttribute("savingsAccount", savingsAccount);
                log.info("Successfully retrieved user account details");
            }else{
                throw new UserException("User is null under homepage");
            }

        }catch (Exception e){
            log.error("Exception occurred at homepage ");
            e.printStackTrace();
            return "generalError";
        }

        return "homePage";

    }

}
