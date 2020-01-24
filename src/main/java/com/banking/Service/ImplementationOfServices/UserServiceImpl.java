package com.banking.Service.ImplementationOfServices;

import com.banking.Repository.RoleRepository;
import com.banking.Repository.UserRepository;
import com.banking.Repository.UserRoleRepository;
import com.banking.Service.AccountService;
import com.banking.Service.UserService;
import com.banking.domain.User;
import com.banking.domain.security.Role;
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
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

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
        return (null!= userRepository.findByEmail(email));
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
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void deactivateUser(String userName) {
        User user = userRepository.findByUsername(userName);
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    /*
        1. Encrypt password
        2. assign roles to user
        3. save/create user
        * */
    @Override
    public void createUser(User user, Set<UserRole> userRoles) {

        User tempUser = userRepository.findByUsername(user.getUsername());

        if(tempUser!=null){
            //add logs
            System.out.println();
        }else{

            //Encrypt password
            String usersEncryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(usersEncryptedPassword);

            //save user after all required updates
            tempUser = userRepository.save(user);

            System.out.println(" **** tempUser :   " + tempUser);
            System.out.println(" **** user :   " + user);

            //assign roles to user
//            Set<UserRole> userRoles2 = user.getUserRoles();//new UserRole();
            Role role = roleRepository.findByName("ROLE_USER");
            UserRole userRole = new UserRole(tempUser,role);
            System.out.println("userRoles.isEmpty() --- "+userRoles.isEmpty());

            //this where it fails badly
            userRoles.add( new UserRole(tempUser,roleRepository.findByName("ROLE_USER")) );

             //save user's role to database
            userRoles.forEach( userRole2->{userRoleRepository.save(userRole2);});

            // ** skipping this part for now
//             tempUser.getUserRoles().addAll(userRoles);

            System.out.println( "UserRoles are empty : "+ user.getUserRoles().isEmpty() + "   size : "+ user.getUserRoles().size() );

            System.out.println(" **** User roles of temp user :   " );

            if(!tempUser.getUserRoles().isEmpty()){
                tempUser.getUserRoles().forEach(System.out::println);
            }

            System.out.println( "Inside user impl  :  " );
            userRoles.forEach(System.out::println);

            System.out.println( "Inside user impl  :  "+ user );

            //assign new accounts to user if accounts are empty
            if( null==tempUser.getAccounts() || tempUser.getAccounts().isEmpty() )
                tempUser =  accountService.createAccounts(tempUser);

        }


    }

}
