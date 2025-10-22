package com.pikolic.meli.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) used to create a new item in the Meli e-commerce API.
 * <p>
 * This record encapsulates the data required for item creation,
 * including validation annotations to ensure that all fields meet
 * the necessary business rules.
 * </p>
 *
 * <p>Example JSON request:</p>
 * <pre>
 * {
 *   "name": "Wireless Mouse",
 *   "description": "Ergonomic wireless mouse with USB receiver",
 *   "price": 249.99
 * }
 * </pre>
 *
 * @param name        the item's name; must not be blank
 * @param description an optional description providing details about the item
 * @param price       the item's price; must not be {@code null} and must be positive
 *
 * @see ItemResponseDTO
 * @see ItemUpdateDTO
 * @see com.pikolic.meli.controller.ItemController
 *
 * @author Angel Lomelí
 */
public record ItemCreateDTO(
        @NotBlank String name,
        String description,
        @NotNull @Positive Double price
) {}
