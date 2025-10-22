package com.pikolic.meli.exception;

import java.time.LocalDateTime;

/**
 * Represents the structure of an error response sent by the API.
 * <p>
 * Contains the HTTP status, error message, request path, and timestamp
 * of when the error occurred.
 * </p>
 *
 * Example JSON response:
 * <pre>
 * {
 *   "status": 404,
 *   "errorMessage": "Client not found",
 *   "path": "/api/v1/clients/1",
 *   "timestamp": "2025-10-22T14:30:00"
 * }
 * </pre>
 *
 * author Angel Lomelí
 *
 * @param status       HTTP status code
 * @param errorMessage Description of the error
 * @param path         Request path that caused the error
 * @param timestamp    Time when the error occurred
 */
public record ErrorResponse(
        int status,
        String errorMessage,
        String path,
        LocalDateTime timestamp
) {}
