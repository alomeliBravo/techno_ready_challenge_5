package com.pikolic.meli.service;

import com.pikolic.meli.dto.order.OrderCreateDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;
import com.pikolic.meli.dto.order.OrderUpdateDTO;

import java.util.List;

/**
 * Service interface for managing orders.
 * <p>
 * Provides methods to create, retrieve, update, and delete order records.
 * </p>
 *
 * author Angel Lomelí
 */
public interface OrderService {

    /**
     * Creates a new order.
     *
     * @param dto the order creation DTO
     * @return the created order response
     */
    OrderResponseDTO create(OrderCreateDTO dto);

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order
     * @return the order response
     */
    OrderResponseDTO getById(Long id);

    /**
     * Retrieves all orders.
     *
     * @return list of order responses
     */
    List<OrderResponseDTO> getAll();

    /**
     * Updates an existing order.
     *
     * @param id the ID of the order
     * @param dto the order update DTO
     * @return the updated order response
     */
    OrderResponseDTO update(Long id, OrderUpdateDTO dto);

    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order
     */
    void delete(Long id);
}
