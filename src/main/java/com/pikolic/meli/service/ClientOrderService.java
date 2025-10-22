package com.pikolic.meli.service;

import com.pikolic.meli.dto.order.OrderCreateForClientDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;

import java.util.List;

/**
 * Service interface for managing orders for a specific client.
 * <p>
 * Provides methods to create, retrieve, update, and delete orders
 * associated with a client.
 * </p>
 *
 * author Angel Lomelí
 */
public interface ClientOrderService {

    /**
     * Retrieves all orders for a given client.
     *
     * @param clientId the ID of the client
     * @return list of order responses
     */
    List<OrderResponseDTO> getOrdersByClientId(Long clientId);

    /**
     * Retrieves a specific order for a given client by order ID.
     *
     * @param clientId the ID of the client
     * @param orderId the ID of the order
     * @return the order response
     */
    OrderResponseDTO getOrderByClientAndId(Long clientId, Long orderId);

    /**
     * Creates a new order for a specific client.
     *
     * @param clientId the ID of the client
     * @param dto the order creation DTO
     * @return the created order response
     */
    OrderResponseDTO createOrderForClient(Long clientId, OrderCreateForClientDTO dto);

    /**
     * Updates an existing order for a specific client.
     *
     * @param clientId the ID of the client
     * @param orderId the ID of the order
     * @param dto the order update DTO
     * @return the updated order response
     */
    OrderResponseDTO updateOrderById(Long clientId, Long orderId, OrderCreateForClientDTO dto);

    /**
     * Deletes an order for a specific client.
     *
     * @param clientId the ID of the client
     * @param orderId the ID of the order
     */
    void deleteOrderById(Long clientId, Long orderId);
}
