package com.pikolic.meli.service;

import com.pikolic.meli.dto.item.ItemCreateDTO;
import com.pikolic.meli.dto.item.ItemResponseDTO;
import com.pikolic.meli.dto.item.ItemUpdateDTO;

import java.util.List;

/**
 * Service interface for managing items.
 * <p>
 * Provides methods to create, retrieve, update, and delete item records.
 * </p>
 *
 * author Angel Lomelí
 */
public interface ItemService {

    /**
     * Creates a new item.
     *
     * @param dto the item creation DTO
     * @return the created item response
     */
    ItemResponseDTO create(ItemCreateDTO dto);

    /**
     * Retrieves an item by its ID.
     *
     * @param id the ID of the item
     * @return the item response
     */
    ItemResponseDTO getById(Long id);

    /**
     * Retrieves all items.
     *
     * @return list of item responses
     */
    List<ItemResponseDTO> getAll();

    /**
     * Updates an existing item.
     *
     * @param id the ID of the item
     * @param dto the item update DTO
     * @return the updated item response
     */
    ItemResponseDTO update(Long id, ItemUpdateDTO dto);

    /**
     * Deletes an item by its ID.
     *
     * @param id the ID of the item
     */
    void delete(Long id);
}
