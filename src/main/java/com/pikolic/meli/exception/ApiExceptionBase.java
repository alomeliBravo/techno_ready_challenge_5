package com.pikolic.meli.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiExceptionBase extends RuntimeException {

    public ApiExceptionBase(String message){
        super(message);
    }

    public abstract HttpStatus getHttpStatus();
}
