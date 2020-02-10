package com.banking.Exceptions;

import com.banking.Log.LogWrapper;

public class UserException extends Exception {

    public static LogWrapper log = LogWrapper.getLogger(UserException.class);

    UserException(){}

    public UserException(String message){
        super(message);
        log.warn("User Not found");
    }

}
