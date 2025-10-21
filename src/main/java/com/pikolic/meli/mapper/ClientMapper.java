package com.pikolic.meli.mapper;

import com.pikolic.meli.dto.client.ClientCreateDTO;
import com.pikolic.meli.dto.client.ClientResponseDTO;
import com.pikolic.meli.dto.client.ClientUpdateDTO;
import com.pikolic.meli.entity.ClientEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClientMapper {

    public static ClientEntity toEntity(ClientCreateDTO dto){
        if(dto == null) return null;

        return ClientEntity.builder()
                .name(dto.name())
                .age(dto.age())
                .email(dto.email())
                .address(dto.address())
                .build();
    }

    public static void updateEntity(ClientEntity entity, ClientUpdateDTO dto){
        if(dto == null) return;

        if(dto.name() != null) entity.setName(dto.name());
        if(dto.age() != null) entity.setAge(dto.age());
        if(dto.email() != null) entity.setEmail(dto.email());
        if(dto.address() != null) entity.setAddress(dto.address());
    }

    public static ClientResponseDTO toResponse(ClientEntity entity){
        if(entity == null) return null;

        return new ClientResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getEmail(),
                entity.getAddress()
        );
    }
}
