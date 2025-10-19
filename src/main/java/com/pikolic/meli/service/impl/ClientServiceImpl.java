package com.pikolic.meli.service.impl;

import com.pikolic.meli.dto.client.ClientCreateDTO;
import com.pikolic.meli.dto.client.ClientResponseDTO;
import com.pikolic.meli.dto.client.ClientUpdateDTO;
import com.pikolic.meli.entity.ClientEntity;
import com.pikolic.meli.exception.NotFoundException;
import com.pikolic.meli.mapper.ClientMapper;
import com.pikolic.meli.repository.ClientRepository;
import com.pikolic.meli.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientResponseDTO create(@Valid ClientCreateDTO dto) {
        ClientEntity client = ClientMapper.toEntity(dto);
        this.clientRepository.save(client);
        return ClientMapper.toResponse(client);
    }

    @Override
    public ClientResponseDTO getById(Long id){
        ClientEntity client = this.clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id " + id));

        return ClientMapper.toResponse(client);
    }

    @Override
    public List<ClientResponseDTO> getAll(){
        List<ClientEntity> clients = this.clientRepository.findAll();

        if(clients.isEmpty()){
            throw new NotFoundException("No clients found");
        }

        return clients.stream().map(ClientMapper::toResponse).toList();
    }

    @Override
    public ClientResponseDTO update(Long id, @Valid ClientUpdateDTO dto){
        ClientEntity client = this.clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id " + id));

        ClientMapper.updateEntity(client, dto);
        ClientEntity clientUpdated = this.clientRepository.save(client);
        return ClientMapper.toResponse(clientUpdated);
    }

    @Override
    public void delete(Long id){
        ClientEntity client = this.clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id " + id));

        this.clientRepository.delete(client);
    }
}
