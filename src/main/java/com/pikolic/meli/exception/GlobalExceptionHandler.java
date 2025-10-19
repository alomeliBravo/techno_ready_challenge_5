package com.pikolic.meli.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

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
}
