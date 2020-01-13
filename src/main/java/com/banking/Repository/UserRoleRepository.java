package com.banking.Repository;

import com.banking.domain.security.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole,Long> {

}
