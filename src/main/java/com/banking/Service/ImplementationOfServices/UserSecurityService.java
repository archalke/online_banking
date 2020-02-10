package com.banking.Service.ImplementationOfServices;

import com.banking.Log.LogWrapper;
import com.banking.Repository.UserRepository;
import com.banking.domain.MyUserDetails;
import com.banking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public static LogWrapper log = LogWrapper.getLogger(UserSecurityService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(null==user){
            log.info(" User not found while login with username "+ username );
            throw new UsernameNotFoundException("User not found "+username);
        }

        log.info("user found with username : "+username);

        return new MyUserDetails(user);

    }

}
