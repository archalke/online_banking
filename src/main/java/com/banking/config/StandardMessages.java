package com.banking.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "com.banking.StandardMessages")
@Data
public class StandardMessages {


    //General Error
    public static String unknownError;// = "Unknown error occurred ";

    //Payee - Log messages
    public static  String payeeDeleted;// = "Payee Deleted Successfully";
    public static  String payeeAdded ;//= "Payee Added Successfully ";
    public static  String payeeLoadIssue;// = "Issue Occurred while loading a payee";

    //User - log messages
    public static  String userAdded;// = "User Added successfully";
    public static  String userDeleted;// = "User Deleted Successfully";
    public static  String userLoadIssue;// = "Issue occurred while loading a user";


}
