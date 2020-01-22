package com.banking.Service;

import com.banking.domain.User;
import com.banking.domain.security.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface UserService {

    User findByUserName(String userName);
    User findByEmail(String email);

    boolean checkUserExists(String userName,String email);
    boolean checkUserNameExists(String userName);
    boolean checkEmailExists(String email);

    List<User> findAllUsers();

    void activateUser(String userName);
    void deactivateUser(String userName);
    void createUser(User user, Set<UserRole> userRoles);

    User findByUsername(String userName);
    void save(User user);

}
