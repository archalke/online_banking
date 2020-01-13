package com.banking.Repository;

import com.banking.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    User findByUsername(String userName);
    User findByEmail(String email);


}
