package com.pikolic.meli.dto.order;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) representing an order returned by the Meli e-commerce API.
 * <p>
 * This record is used to transfer order data from the backend to the client,
 * typically in response to retrieval, creation, or update operations.
 * </p>
 *
 * <p>Example JSON response:</p>
 * <pre>
 * {
 *   "id": 1,
 *   "client_id": 1,
 *   "item_id": 2,
 *   "purchaseDate": "2025-10-22",
 *   "total": 299.99
 * }
 * </pre>
 *
 * @param id           the unique identifier of the order
 * @param client_id    the ID of the client who placed the order
 * @param item_id      the ID of the item that was ordered
 * @param purchaseDate the date when the order was placed
 * @param total        the total amount of the order
 *
 * @see OrderCreateDTO
 * @see OrderCreateForClientDTO
 * @see OrderUpdateDTO
 * @see com.pikolic.meli.controller.OrderController
 * @see com.pikolic.meli.controller.ClientOrderController
 *
 * author Angel Lomelí
 */
public record OrderResponseDTO(
        Long id,
        Long client_id,
        Long item_id,
        LocalDate purchaseDate,
        Double total
) {}
