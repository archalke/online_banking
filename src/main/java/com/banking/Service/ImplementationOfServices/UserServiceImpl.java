package com.banking.Service.ImplementationOfServices;

import com.banking.Log.LogWrapper;
import com.banking.Repository.UserRepository;
import com.banking.Service.AccountService;
import com.banking.Service.UserService;
import com.banking.domain.Account;
import com.banking.domain.User;
import com.banking.domain.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public static LogWrapper log = LogWrapper.getLogger(UserServiceImpl.class);

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
        return checkUserNameExists(userName) || checkEmailExists(email);
    }

    @Override
    public boolean checkUserNameExists(String userName) {
        return (null!= userRepository.findByUsername(userName));
    }

    @Override
    public boolean checkEmailExists(String email) {
        log.debug("");
        return (null!= userRepository.findByEmail(email));
    }

    @Override
    public List<User> findAllUsers() {
        log.debug("Searching all users");
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public void activateUser(String userName) {
        log.debug("Activating the user");
        User user = userRepository.findByUsername(userName);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void deactivateUser(String userName) {
        log.debug("Deactivating the user");
        User user = userRepository.findByUsername(userName);
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        log.debug("Looking for user : "+ userName);
        return  userRepository.findByUsername(userName);
    }

    @Override
    public void save(User user) {
        log.debug(" saving a user updates");
        userRepository.save(user);
    }

    @Override
    public User findByAccountNumber(Long accountNumber) {
        log.debug("find by account number");
        Account account = accountService.findByAccountNumber(accountNumber);
        return userRepository.findByUsername(account.getUser().getUsername());
    }

    /*
        1. Encrypt password
        2. assign roles to user
        3. save/create user
        * */
    @Override
    public void createUser(User user, Set<UserRole> userRoles) {

        log.debug("create new user");

        try{
            User tempUser = userRepository.findByUsername(user.getUsername());
            if(tempUser!=null){
                log.info("User already exists"+ user.getUsername() + " and "+ user.getEmail());
            }else {
                //Encrypt password
                String usersEncryptedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(usersEncryptedPassword);
                log.debug("Successfully encrypted a password");

                user.setRoles("USER_ROLE");

                tempUser = userRepository.save(user);

                log.debug(" Successfully added user to database");

                //assign new accounts to user if accounts are empty
                if (null == tempUser.getAccounts() || tempUser.getAccounts().isEmpty()) {
                    tempUser = accountService.createAccounts(tempUser);
                }
            }
        }catch (Exception e){
            log.error("Unable to create user");
            e.printStackTrace();
            throw e;
        }

    }

}
