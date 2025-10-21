package com.pikolic.meli.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiExceptionBase{
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
