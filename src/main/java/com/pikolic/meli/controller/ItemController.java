package com.pikolic.meli.controller;

import com.pikolic.meli.dto.item.ItemCreateDTO;
import com.pikolic.meli.dto.item.ItemResponseDTO;
import com.pikolic.meli.dto.item.ItemUpdateDTO;
import com.pikolic.meli.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing items in the Meli e-commerce API.
 * <p>
 * This controller exposes endpoints for creating, retrieving, updating, and deleting items.
 * All operations are delegated to the {@link ItemService}.
 * </p>
 *
 * <p><b>Base path:</b> {@code /api/v1/items}</p>
 *
 * <p>Example endpoints:</p>
 * <ul>
 *     <li>{@code POST /api/v1/items} – Create a new item</li>
 *     <li>{@code GET /api/v1/items} – Retrieve all items</li>
 *     <li>{@code GET /api/v1/items/{id}} – Retrieve a specific item by ID</li>
 *     <li>{@code PUT /api/v1/items/{id}} – Update an existing item</li>
 *     <li>{@code DELETE /api/v1/items/{id}} – Delete an item by ID</li>
 * </ul>
 *
 * @author Angel Lomelí
 * @see ItemService
 * @see ItemCreateDTO
 * @see ItemUpdateDTO
 * @see ItemResponseDTO
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
class ItemController {

    /** Service layer responsible for handling item-related operations. */
    private final ItemService itemService;

    /**
     * Creates a new item.
     *
     * @param dto the data transfer object containing the new item's details
     * @return a {@link ResponseEntity} containing the created {@link ItemResponseDTO}
     *         and an HTTP 201 (Created) status
     * @throws jakarta.validation.ConstraintViolationException if validation on {@code dto} fails
     */
    @PostMapping({"", "/"})
    public ResponseEntity<ItemResponseDTO> addItem(@Valid @RequestBody ItemCreateDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.itemService.create(dto));
    }

    /**
     * Retrieves a specific item by its unique ID.
     *
     * @param id the ID of the item to retrieve
     * @return a {@link ResponseEntity} containing the requested {@link ItemResponseDTO}
     *         and an HTTP 200 (OK) status
     * @throws com.pikolic.meli.exception.NotFoundException if the item does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(this.itemService.getById(id));
    }

    /**
     * Retrieves all items in the system.
     *
     * @return a {@link ResponseEntity} containing a list of {@link ItemResponseDTO}
     *         and an HTTP 200 (OK) status
     */
    @GetMapping({"", "/"})
    public ResponseEntity<List<ItemResponseDTO>> getAllItems() {
        return ResponseEntity.ok(this.itemService.getAll());
    }

    /**
     * Updates an existing item by its unique ID.
     *
     * @param id  the ID of the item to update
     * @param dto the data transfer object containing the updated item details
     * @return a {@link ResponseEntity} containing the updated {@link ItemResponseDTO}
     *         and an HTTP 200 (OK) status
     * @throws com.pikolic.meli.exception.NotFoundException if the item does not exist
     * @throws jakarta.validation.ConstraintViolationException if validation on {@code dto} fails
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItemById(@PathVariable Long id, @Valid @RequestBody ItemUpdateDTO dto) {
        return ResponseEntity.ok(this.itemService.update(id, dto));
    }

    /**
     * Deletes an item by its unique ID.
     *
     * @param id the ID of the item to delete
     * @return a {@link ResponseEntity} with an HTTP 204 (No Content) status if deletion is successful
     * @throws com.pikolic.meli.exception.NotFoundException if the item does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable Long id) {
        this.itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
