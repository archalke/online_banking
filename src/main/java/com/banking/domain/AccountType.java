package com.banking.domain;

public enum AccountType {
    //earlier c mapped to checking and now changes to primary
    S("SAVING"), C("PRIMARY");
    //declaring private variables for getting values
    private String type;
    //get method
    public String getType(){
        return this.type;
    }

    //enum constructor - can't be public or protected
    AccountType(String type){
        this.type = type;
    }

    public static int PRIMARY_ACCOUNT_INDEX = 0;
    public  static int SAVINGS_ACCOUNT_INDEX = 1;

}
