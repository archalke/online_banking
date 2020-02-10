package com.banking.Repository;

import com.banking.domain.Account;
import com.banking.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String userName);
    User findByEmail(String email);


    //this is customised query
//    @Query("select * from user where id = ( select user_id from account where account_number = :accountNumber )")
//    User findByAccountNumber(@Param("accountNumber") Long accountNumber);
//    User findByAccountNumber(Long accountNumber);

}
