package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NullArgumentException extends Exception {

    public static String ERR_ARGUMENTS_NULL = "Null argments were passed";

    public NullArgumentException(String message){
        super(message);
    }
}
