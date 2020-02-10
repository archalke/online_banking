package com.banking.Log;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

/**
Created by Avinash on 02/08/2020
*/
public class LogWrapper {

    private final Logger logger;

    private LogWrapper(Logger logger){
        this.logger = logger;
    }

    public static LogWrapper getLogger(Class<?> classs){
        return  new LogWrapper((Logger) LoggerFactory.getLogger(classs));
    }

    public void debug(String message){
        if(!logger.isDebugEnabled())
            return;
        logger.debug(message);
    }

    public void info(String message){
        if(!logger.isInfoEnabled())
            return;
        logger.info(message);
    }

    public void error(String message){
        if(!logger.isErrorEnabled())
            return;
        logger.error(message);
    }

    public void warn(String message){
        if(!logger.isWarnEnabled())
            return;
        logger.warn(message);
    }

}
