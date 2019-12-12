package Controllers;

import model.LoginUser;
import Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/accounts")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping(path = "/login")
    public @ResponseBody  String Login(@RequestParam String username,@RequestParam String password){

        Iterable<LoginUser> loginTest = loginRepository.findAll();

        for (LoginUser loginUser : loginTest ){
            if(username.equals(loginUser.getUsername()) && password.equals(loginUser.getPassword())){
                return "found";
            }
            else {
                return "not found";
            }
        };
        return "Login failed";

    }


    @PostMapping(path="/adduser")
    public @ResponseBody String addUser(@RequestParam String username,@RequestParam String password){

        LoginUser loginUser = new LoginUser(username,password);
        loginRepository.save(loginUser);
        return "user added successfully";
    }

}
