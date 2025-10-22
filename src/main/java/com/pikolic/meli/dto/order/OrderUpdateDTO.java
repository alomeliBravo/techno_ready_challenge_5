package com.pikolic.meli.dto.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) used to update an existing order in the Meli e-commerce API.
 * <p>
 * This record is typically used in HTTP PUT requests to modify order details.
 * It includes validation constraints to ensure that client and item IDs are positive
 * and that the total amount is greater than zero.
 * </p>
 *
 * <p>Example JSON request:</p>
 * <pre>
 * {
 *   "clientId": 1,
 *   "itemId": 2,
 *   "purchaseDate": "2025-10-23",
 *   "total": 349.99
 * }
 * </pre>
 *
 * @param clientId     the updated client ID; must be positive if provided
 * @param itemId       the updated item ID; must be positive if provided
 * @param purchaseDate the updated purchase date
 * @param total        the updated total amount; must be greater than zero
 *
 * @see OrderCreateDTO
 * @see OrderCreateForClientDTO
 * @see OrderResponseDTO
 * @see com.pikolic.meli.controller.OrderController
 *
 * author Angel Lomelí
 */
public record OrderUpdateDTO(
        @Positive(message = "clientId must be positive")
        Long clientId,

        @Positive(message = "itemId must be positive")
        Long itemId,

        LocalDate purchaseDate,

        @DecimalMin(value = "0.01", message = "total must be greater than zero")
        Double total
) {}
