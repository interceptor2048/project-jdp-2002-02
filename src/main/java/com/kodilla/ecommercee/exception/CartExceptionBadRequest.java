package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CartExceptionBadRequest extends Exception {

    public static String ERR_CART_EXISTS = "Cart already exists";
    public static String ERR_CART_PATH = "Cart path id does not match cart id";

    public CartExceptionBadRequest(String message){
        super(message);
    }
}
