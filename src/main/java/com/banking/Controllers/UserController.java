package com.banking.Controllers;

import com.banking.Repository.UserRepository;
import com.banking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(path="/onlinebanking")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/profile")
    public String profile(Principal principal, Model model){

        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user",user);
        return "profile";

    }



}
