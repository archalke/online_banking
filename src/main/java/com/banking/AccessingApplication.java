package com.banking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan
public class AccessingApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingApplication.class,args);
    }

   /* @Bean
    public CommandLineRunner demo(AccountRepository accountRepository){

        return (args) -> {

            //save few customers
            accountRepository.save( new Account(123,'A'));
            accountRepository.save( new Account(1234,'A'));
            accountRepository.save( new Account(12345,'A'));

            //fetch all customers
            log.info("Customers found with findAll()");
            log.info(" ----------------- ");

            for(Account account:accountRepository.findAll()){
                log.info( account.toString() );
            }

            log.info(" ----------------- ");



        };
    }
*/



}
