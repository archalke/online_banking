package com.banking.Service.ImplementationOfServices;

import com.banking.Repository.UserRepository;
import com.banking.Service.AccountService;
import com.banking.Service.UserService;
import com.banking.model.Account;
import com.banking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService accountService;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkUserExists(String userName, String email) {

        if(checkUserNameExists(userName)||checkEmailExists(email)) {
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean checkUserNameExists(String userName) {
        return (null!= userRepository.findByUsername(userName));
    }

    @Override
    public boolean checkEmailExists(String email) {
        return (null!= userRepository.findByEmail(email));
    }

    @Override
    public void save(User user) {

         List<Account> accounts = user.getAccounts();
        Long count = userRepository.count();

        System.out.println( "User Count :  "+ userRepository.count());

        User savedUser =  userRepository.save(user);

        if(!accounts.isEmpty()){
            accountService.createAccount(accounts.get(0),"S", savedUser);
            accountService.createAccount(accounts.get(1),"C", savedUser);
        }

    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public void activateUser(String userName) {
        User user = userRepository.findByUsername(userName);
        userRepository.save(user);
    }

    @Override
    public void deactivateUser(String userName) {
        User user = userRepository.findByUsername(userName);
        userRepository.save(user);
    }

}
