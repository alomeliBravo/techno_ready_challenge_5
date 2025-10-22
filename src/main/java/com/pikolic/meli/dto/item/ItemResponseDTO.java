package com.pikolic.meli.dto.item;

/**
 * Data Transfer Object (DTO) representing an item returned by the Meli e-commerce API.
 * <p>
 * This record is used to transfer item data from the backend to the client,
 * typically in response to retrieval, creation, or update operations.
 * </p>
 *
 * <p>Example JSON response:</p>
 * <pre>
 * {
 *   "id": 1,
 *   "name": "Wireless Mouse",
 *   "description": "Ergonomic wireless mouse with USB receiver",
 *   "price": 249.99
 * }
 * </pre>
 *
 * @param id          the unique identifier of the item
 * @param name        the name of the item
 * @param description a description providing details about the item
 * @param price       the price of the item
 *
 * @see ItemCreateDTO
 * @see ItemUpdateDTO
 * @see com.pikolic.meli.controller.ItemController
 *
 * @author Angel Lomelí
 */
public record ItemResponseDTO(
        Long id,
        String name,
        String description,
        Double price
) {}
