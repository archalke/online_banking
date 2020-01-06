package com.banking.model;

public enum AccountType {
    S("SAVING"), C("CHECKING");
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
}
