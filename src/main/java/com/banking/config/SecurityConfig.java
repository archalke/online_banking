package com.banking.config;


import com.banking.Service.ImplementationOfServices.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserSecurityService userSecurityService;

    //Encrypt password
    private static final String AUSTIN = "Austin";
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12,new SecureRandom(AUSTIN.getBytes()));
    }

    //Authorize user
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }



    //add security - for requests

    //Pulic matchers for URL's
    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",            "/css/**",            "/js/**",
            "/images/**",            "/",            "/about/**",
            "/contact/**",            "/error/**/*",            "/console/**",            "/signup"
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS)
                .permitAll().anyRequest().authenticated();

        System.out.println("Inside security config http method  "  );

        http
                .csrf().disable().cors().disable()

                .formLogin().failureUrl("/index?error")
                            .defaultSuccessUrl("/onlinebanking/homePage")
                            .loginPage("/index")
                            .permitAll()

                .and()

                .logout().logoutRequestMatcher( new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/index?logout")
                        .deleteCookies("remember-me")
                        .permitAll()

                .and()

                .rememberMe();

    }
}
