package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CartExceptionNotFound extends Exception {

    public static String ERR_PRODUCT_NOT_FOUND= "Cannot add product to cart because it does not exists";
    public static String ERR_PRODUCT_NOT_AVAILABLE= "Cannot add product to cart because it is currently not available";
    public static String ERR_CART_NOT_FOUND = "Cannot find cart by given Id";
    public static String ERR_CART_EXISTS = "Cart already exists";

    public CartExceptionNotFound(String message){
        super(message);
    }
}
