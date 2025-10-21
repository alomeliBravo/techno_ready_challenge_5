package com.pikolic.meli.exception;

import org.springframework.http.HttpStatus;

public class ForbbidenException extends ApiExceptionBase{
    public ForbbidenException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
