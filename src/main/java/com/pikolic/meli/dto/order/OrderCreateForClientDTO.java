package com.pikolic.meli.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) used to create a new order for a specific client
 * in the Meli e-commerce API.
 * <p>
 * This record is typically used when creating an order directly for a client,
 * where the client ID is obtained from the request path, and only the item ID
 * is required in the request body.
 * </p>
 *
 * <p>Example JSON request:</p>
 * <pre>
 * {
 *   "itemId": 2
 * }
 * </pre>
 *
 * @param itemId the ID of the item being ordered; must not be null and must be positive
 *
 * @see OrderResponseDTO
 * @see com.pikolic.meli.controller.ClientOrderController
 *
 * author Angel Lomelí
 */
public record OrderCreateForClientDTO(
        @NotNull(message = "item_id is required")
        @Positive(message = "item_id must be positive")
        Long itemId
) {}
