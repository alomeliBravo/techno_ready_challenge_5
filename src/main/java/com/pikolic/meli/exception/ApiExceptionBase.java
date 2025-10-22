package com.pikolic.meli.exception;

import org.springframework.http.HttpStatus;

/**
 * Base class for API exceptions in the Meli e-commerce system.
 * <p>
 * All custom API exceptions should extend this class and provide
 * the corresponding HTTP status code.
 * </p>
 *
 * Example usage: create a {@code NotFoundException} extending this class.
 * </p>
 *
 * author Angel Lomelí
 */
public abstract class ApiExceptionBase extends RuntimeException {

    /**
     * Constructor with an error message.
     *
     * @param message the exception message
     */
    public ApiExceptionBase(String message){
        super(message);
    }

    /**
     * Returns the HTTP status associated with this exception.
     *
     * @return the HTTP status code
     */
    public abstract HttpStatus getHttpStatus();
}
