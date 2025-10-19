package com.pikolic.meli.service;

import com.pikolic.meli.dto.client.ClientCreateDTO;
import com.pikolic.meli.dto.client.ClientResponseDTO;
import com.pikolic.meli.dto.client.ClientUpdateDTO;

import java.util.List;

public interface ClientService {
    ClientResponseDTO create(ClientCreateDTO dto);
    ClientResponseDTO getById(Long id);
    List<ClientResponseDTO> getAll();
    ClientResponseDTO update(Long id, ClientUpdateDTO dto);
    void delete(Long id);
}
