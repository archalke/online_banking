package com.banking.Repository;

import com.banking.model.Consumer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends CrudRepository<Consumer, Long> {


    Consumer findByUserName(String userName);


    boolean findByEmail(String email);
}
