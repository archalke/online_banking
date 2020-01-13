package com.banking.Repository;

import com.banking.domain.security.Role;
import com.banking.domain.security.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByName(String role_user);
}