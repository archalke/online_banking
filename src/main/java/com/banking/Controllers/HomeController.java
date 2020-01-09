package com.banking.Controllers;

import com.banking.Repository.UserRepository;
import com.banking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

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
        String userName = user.getUsername();
        String email = user.getEmail();

        return "signup";
    }

}
