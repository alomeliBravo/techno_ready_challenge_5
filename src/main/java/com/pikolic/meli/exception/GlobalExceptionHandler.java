package com.pikolic.meli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
}
