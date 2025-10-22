package com.pikolic.meli.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) used to update an existing client's information
 * in the Meli e-commerce API.
 * <p>
 * This record encapsulates the data required to modify a client's details,
 * including validation constraints to ensure input consistency.
 * </p>
 *
 * <p>Unlike {@link ClientCreateDTO}, this DTO is typically used when updating
 * an existing client identified by its unique ID in the request path.</p>
 *
 * <p>Example JSON request:</p>
 * <pre>
 * {
 *   "name": "Angel Lomelí Bravo",
 *   "age": 25,
 *   "email": "alomelibravo@gmail.com",
 *   "address": "Avenida Los Venados 456"
 * }
 * </pre>
 *
 * @param name    the updated client's full name; must not be blank
 * @param age     the updated client's age; must be positive if provided
 * @param email   the updated client's email address; must not be blank
 * @param address the updated client's address; optional
 *
 * @author Angel Lomelí
 * @see ClientCreateDTO
 * @see ClientResponseDTO
 */
public record ClientUpdateDTO(
        @NotBlank(message = "name is required")
        String name,

        @Positive(message = "age must be positive")
        Integer age,

        @NotBlank(message = "email is required")
        String email,

        String address
) {}
