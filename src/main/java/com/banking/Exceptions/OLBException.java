package com.banking.Exceptions;

import com.banking.Log.LogWrapper;

public class OLBException extends Exception {

    public static LogWrapper log = LogWrapper.getLogger(OLBException.class);

    OLBException(){}

    public OLBException(String str){
        super(str);
    }



}
