package com.pikolic.meli.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a requested resource is not found.
 * <p>
 * This exception returns an HTTP 404 (Not Found) status.
 * </p>
 *
 * Example usage: trying to retrieve a client or item that does not exist.
 *
 * author Angel Lomelí
 */
public class NotFoundException extends ApiExceptionBase {

    /**
     * Constructor with an error message.
     *
     * @param errorMessage the exception message
     */
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns the HTTP status for this exception.
     *
     * @return HTTP 404 Not Found
     */
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
