package com.banking.Controllers;

import com.banking.Repository.ConsumerRepository;
import com.banking.model.Consumer;
import com.banking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    ConsumerRepository consumerRepository;

    @RequestMapping("/")
    public String home(){
        return"redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){

        System.out.println("Inside /index ");
        return "index";
    }

    @GetMapping("/signup")
    public String signUpGet(Model model){
        Consumer consumer = new Consumer();
        model.addAttribute("user",consumer);
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpPost(@ModelAttribute("user") Consumer consumer, Model model){

        String userName = consumer.getUserName();
        String email = consumer.getEmail();

//        if( (null!=consumerRepository.findByUserName(userName)) && (null!=consumerRepository.findByEmail(email)) ){
//
//        }

        return "signup";
    }
    


}
