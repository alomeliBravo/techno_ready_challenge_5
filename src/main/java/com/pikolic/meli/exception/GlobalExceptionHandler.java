package com.pikolic.meli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Global exception handler for the Meli e-commerce API.
 * <p>
 * Catches and handles different types of exceptions thrown by controllers
 * and returns a structured {@link ErrorResponse} with the appropriate HTTP status.
 * </p>
 *
 * Handles:
 * <ul>
 *     <li>{@link ApiExceptionBase}: custom API exceptions with their HTTP status</li>
 *     <li>{@link MethodArgumentNotValidException}: validation errors (HTTP 400)</li>
 *     <li>{@link Exception}: generic exceptions (HTTP 500)</li>
 * </ul>
 *
 * author Angel Lomelí
 */
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ApiExceptionBase.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiExceptionBase ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse(
                ex.getHttpStatus().value(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=",""),
                LocalDateTime.now()
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentException(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                String.join("| ", errors),
                request.getDescription(false).replace("uri=",""),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Sorry. An unexpected error has occurred. Try again later",
                request.getDescription(false).replace("uri=",""),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
