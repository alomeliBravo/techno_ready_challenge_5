package com.pikolic.meli.dto.client;

/**
 * Data Transfer Object (DTO) representing a client's data returned by the Meli e-commerce API.
 * <p>
 * This record is used to send client information in API responses after creation,
 * retrieval, or updates. It does not include validation annotations since it is
 * intended for output only.
 * </p>
 *
 * <p>Example JSON response:</p>
 * <pre>
 * {
 *   "id": 1,
 *   "name": "Angel Lomelí",
 *   "age": 24,
 *   "email": "alomelibravo@gmail.com",
 *   "address": "Avenida Los Venados 123"
 * }
 * </pre>
 *
 * @param id       the unique identifier of the client
 * @param name     the client's full name
 * @param age      the client's age
 * @param email    the client's email address
 * @param address  the client's address
 *
 * @author Angel Lomelí
 */
public record ClientResponseDTO(
        Long id,
        String name,
        Integer age,
        String email,
        String address
) {}
