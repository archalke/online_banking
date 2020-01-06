package com.banking.Service;

import com.banking.model.Consumer;

import java.util.List;

public interface ConsumerService {

    Consumer findByUserName(String userName);
    Consumer findByEmail(String email);

    boolean checkUserExists(String userName,String email);
    boolean checkUserNameExists(String userName);
    boolean checkEmailExists(String email);

    void save(Consumer consumer);

    List<Consumer> findAllConsumers();

    void activateUser(String userName);
    void deactivateUser(String userName);


}
