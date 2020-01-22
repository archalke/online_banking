package com.banking.Controllers;

import com.banking.Service.UserService;
import com.banking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping(path="/onlinebanking/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/profile")
    public String profile(Principal principal, Model model){

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user",user);
        return "profile";

    }

    @PostMapping(value = "/profile")
    public String profilePost(@ModelAttribute("user")User newUser, Model model){

        User user = userService.findByUsername(newUser.getUsername());
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setPhone(newUser.getPhone());

        model.addAttribute("user",user);

        userService.save(user);

        return "profile";

    }



}
