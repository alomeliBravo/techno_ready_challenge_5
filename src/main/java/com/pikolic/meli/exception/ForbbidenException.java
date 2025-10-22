package com.pikolic.meli.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a client attempts an action they are not allowed to perform.
 * <p>
 * This exception returns an HTTP 403 (Forbidden) status.
 * </p>
 *
 * Example usage: trying to access a resource without sufficient permissions.
 *
 * author Angel Lomelí
 */
public class ForbbidenException extends ApiExceptionBase {

    /**
     * Constructor with an error message.
     *
     * @param message the exception message
     */
    public ForbbidenException(String message) {
        super(message);
    }

    /**
     * Returns the HTTP status for this exception.
     *
     * @return HTTP 403 Forbidden
     */
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
