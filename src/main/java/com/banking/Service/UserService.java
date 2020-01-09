package com.banking.Service;

import com.banking.model.User;

import java.util.List;

public interface UserService {

    User findByUserName(String userName);
    User findByEmail(String email);

    boolean checkUserExists(String userName,String email);
    boolean checkUserNameExists(String userName);
    boolean checkEmailExists(String email);

    void save(User user);

    List<User> findAllUsers();

    void activateUser(String userName);
    void deactivateUser(String userName);


}
