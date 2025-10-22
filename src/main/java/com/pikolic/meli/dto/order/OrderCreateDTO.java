package com.pikolic.meli.dto.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) used to create a new order in the Meli e-commerce API.
 * <p>
 * This record encapsulates all the required information to register a new order,
 * including client ID, item ID, purchase date, and total amount. Validation constraints
 * ensure that all fields meet business rules before processing.
 * </p>
 *
 * <p>Example JSON request:</p>
 * <pre>
 * {
 *   "clientId": 1,
 *   "itemId": 2,
 *   "purchaseDate": "2025-10-22",
 *   "total": 299.99
 * }
 * </pre>
 *
 * @param clientId     the ID of the client placing the order; must not be null and must be positive
 * @param itemId       the ID of the item being ordered; must not be null and must be positive
 * @param purchaseDate the date of purchase; must not be null
 * @param total        the total amount of the order; must not be null and must be greater than zero
 *
 * @see OrderResponseDTO
 * @see OrderUpdateDTO
 * @see com.pikolic.meli.controller.OrderController
 *
 * author Angel Lomelí
 */
public record OrderCreateDTO(
        @NotNull(message = "client_id is required")
        @Positive(message = "client_id must be positive")
        Long clientId,

        @NotNull(message = "item_id is required")
        @Positive(message = "item_id must be positive")
        Long itemId,

        @NotNull(message = "purchaseDate is required")
        LocalDate purchaseDate,

        @NotNull(message = "total is required")
        @DecimalMin(value = "0.01", message = "total must be greater than zero")
        Double total
) {}
