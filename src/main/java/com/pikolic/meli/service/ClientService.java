package com.pikolic.meli.service;

import com.pikolic.meli.dto.client.ClientCreateDTO;
import com.pikolic.meli.dto.client.ClientResponseDTO;
import com.pikolic.meli.dto.client.ClientUpdateDTO;

import java.util.List;

/**
 * Service interface for managing clients.
 * <p>
 * Provides methods to create, retrieve, update, and delete client records.
 * </p>
 *
 * author Angel Lomelí
 */
public interface ClientService {

    /**
     * Creates a new client.
     *
     * @param dto the client creation DTO
     * @return the created client response
     */
    ClientResponseDTO create(ClientCreateDTO dto);

    /**
     * Retrieves a client by its ID.
     *
     * @param id the ID of the client
     * @return the client response
     */
    ClientResponseDTO getById(Long id);

    /**
     * Retrieves all clients.
     *
     * @return list of client responses
     */
    List<ClientResponseDTO> getAll();

    /**
     * Updates an existing client.
     *
     * @param id the ID of the client
     * @param dto the client update DTO
     * @return the updated client response
     */
    ClientResponseDTO update(Long id, ClientUpdateDTO dto);

    /**
     * Deletes a client by its ID.
     *
     * @param id the ID of the client
     */
    void delete(Long id);
}
