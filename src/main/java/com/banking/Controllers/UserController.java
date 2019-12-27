package com.banking.Controllers;

import com.banking.Repository.UserRepository;
import com.banking.model.Consumer;
import com.banking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/onlinebanking")
public class UserController {

    @Autowired
    private UserRepository loginRepository;

    @GetMapping(path = "/login")
    public @ResponseBody  String Login(@RequestParam String username,@RequestParam String password){

        Iterable<User> loginTest = loginRepository.findAll();

        for (User loginUser : loginTest ){
            if(username.equals(loginUser.getUsername()) && password.equals(loginUser.getPassword())){
                return "found";
            }
            else {
                return "not found";
            }
        }
        return "Login failed";
    }

    @PostMapping(path="/adduser")
    public @ResponseBody String addUser(@RequestParam String username,@RequestParam String password){

        User loginUser = new User(username,password);

        loginRepository.save(loginUser);

        return "user added successfully";

    }


    private Consumer addConsumer(){
        Consumer consumer = new Consumer();
        return consumer;
    }


}
