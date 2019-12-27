package com.banking.Controllers;

import com.banking.Repository.ConsumerRepository;
import com.banking.model.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller//indicates this class is controller
@RequestMapping(path="/onlinebanking")//indicates URL starts with onlinebanking after application context
public class ConsumerController {

    @Autowired
    private ConsumerRepository consumerRepository;

    @GetMapping(path="/consumer/{consumerid}")
    public @ResponseBody Optional<Consumer> getConsumerDetails(@PathVariable("consumerid") Long consumerId ) {
        return consumerRepository.findById(consumerId);
    }


}
