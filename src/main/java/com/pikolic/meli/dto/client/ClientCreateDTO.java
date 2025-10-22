package com.pikolic.meli.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) used to create a new client in the Meli e-commerce API.
 * <p>
 * This record encapsulates all the information required to register a new client,
 * including validation constraints to ensure data integrity.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {
 *   "name": "Angel Lomelí",
 *   "age": 24,
 *   "email": "alomelibravo@gmail.com",
 *   "address": "Avenida Los Venados 123"
 * }
 * </pre>
 *
 * @param name    the client's full name; must not be blank
 * @param age     the client's age; must be positive
 * @param email   the client's email address; must not be blank
 * @param address the client's address; optional
 *
 * @author Angel Lomelí
 */
public record ClientCreateDTO(
        @NotBlank(message = "name is required")
        String name,

        @Positive(message = "age must be positive")
        Integer age,

        @NotBlank(message = "email is required")
        String email,

        String address
) {}
