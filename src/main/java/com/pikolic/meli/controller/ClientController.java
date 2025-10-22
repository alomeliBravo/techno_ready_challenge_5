package com.pikolic.meli.controller;

import com.pikolic.meli.dto.client.ClientCreateDTO;
import com.pikolic.meli.dto.client.ClientResponseDTO;
import com.pikolic.meli.dto.client.ClientUpdateDTO;
import com.pikolic.meli.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing clients in the Meli e-commerce API.
 * <p>
 * This controller exposes endpoints for creating, retrieving, updating, and deleting client records.
 * All operations are delegated to the {@link ClientService}.
 * </p>
 *
 * <p><b>Base path:</b> {@code /api/v1/clients}</p>
 *
 * <p>Example endpoints:</p>
 * <ul>
 *     <li>{@code POST /api/v1/clients} – Create a new client</li>
 *     <li>{@code GET /api/v1/clients} – Retrieve all clients</li>
 *     <li>{@code GET /api/v1/clients/{id}} – Retrieve a specific client by ID</li>
 *     <li>{@code PUT /api/v1/clients/{id}} – Update a client by ID</li>
 *     <li>{@code DELETE /api/v1/clients/{id}} – Delete a client by ID</li>
 * </ul>
 *
 * @author Angel Lomelí
 * @see ClientService
 * @see ClientCreateDTO
 * @see ClientUpdateDTO
 * @see ClientResponseDTO
 */
@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    /** Service layer for handling client-related operations. */
    private final ClientService clientService;

    /**
     * Creates a new client.
     *
     * @param dto the data transfer object containing the new client's details
     * @return a {@link ResponseEntity} containing the created {@link ClientResponseDTO}
     *         and an HTTP 201 (Created) status
     */
    @PostMapping({"", "/"})
    public ResponseEntity<ClientResponseDTO> addClient(@Valid @RequestBody ClientCreateDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.clientService.create(dto));
    }

    /**
     * Retrieves all registered clients.
     *
     * @return a {@link ResponseEntity} containing a list of {@link ClientResponseDTO}
     *         and an HTTP 200 (OK) status
     */
    @GetMapping({"", "/"})
    public ResponseEntity<List<ClientResponseDTO>> getAll() {
        return ResponseEntity.ok(this.clientService.getAll());
    }

    /**
     * Retrieves a specific client by its unique ID.
     *
     * @param id the ID of the client to retrieve
     * @return a {@link ResponseEntity} containing the corresponding {@link ClientResponseDTO}
     *         and an HTTP 200 (OK) status
     * @throws com.pikolic.meli.exception.NotFoundException if the client does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.clientService.getById(id));
    }

    /**
     * Updates an existing client's information.
     *
     * @param id  the ID of the client to update
     * @param dto the data transfer object containing the updated client details
     * @return a {@link ResponseEntity} containing the updated {@link ClientResponseDTO}
     *         and an HTTP 200 (OK) status
     * @throws com.pikolic.meli.exception.NotFoundException if the client does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientUpdateDTO dto) {
        return ResponseEntity.ok(this.clientService.update(id, dto));
    }

    /**
     * Deletes a client by its unique ID.
     *
     * @param id the ID of the client to delete
     * @return a {@link ResponseEntity} with an HTTP 204 (No Content) status if deletion is successful
     * @throws com.pikolic.meli.exception.NotFoundException if the client does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
