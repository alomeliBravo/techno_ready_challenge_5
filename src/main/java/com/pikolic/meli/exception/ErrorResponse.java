package com.pikolic.meli.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String errorMessage,
        String path,
        LocalDateTime timestamp
) {}
