package com.kodilla.ecommercee.exception;

public class UserException extends Exception {

    public static String ERR_USER_NOT_FOUND= "Cannot add product to cart because it does not exists";

    public UserException(String message){
        super(message);
    }
}
