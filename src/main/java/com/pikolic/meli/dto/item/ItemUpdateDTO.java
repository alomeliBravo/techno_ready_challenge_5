package com.pikolic.meli.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) used to update an existing item in the Meli e-commerce API.
 * <p>
 * This record is typically used in HTTP PUT requests to modify an item's details.
 * Only fields included in this DTO can be updated.
 * </p>
 *
 * <p>Example JSON request:</p>
 * <pre>
 * {
 *   "name": "Wireless Keyboard",
 *   "description": "Compact Bluetooth keyboard with rechargeable battery",
 *   "price": 499.99
 * }
 * </pre>
 *
 * @param name        the updated name of the item (must not be blank)
 * @param description the updated description of the item (optional)
 * @param price       the updated price of the item (must be positive)
 *
 * @see ItemCreateDTO
 * @see ItemResponseDTO
 * @see com.pikolic.meli.controller.ItemController
 *
 * author Angel Lomelí
 */
public record ItemUpdateDTO(
        @NotBlank String name,
        String description,
        @Positive Double price
) {}
