package com.pikolic.meli.mapper;

import com.pikolic.meli.dto.client.ClientCreateDTO;
import com.pikolic.meli.dto.client.ClientResponseDTO;
import com.pikolic.meli.dto.client.ClientUpdateDTO;
import com.pikolic.meli.entity.ClientEntity;
import lombok.NoArgsConstructor;

/**
 * Mapper class for converting between {@link ClientEntity} and client DTOs.
 * <p>
 * Provides static methods to:
 * <ul>
 *     <li>Convert a {@link ClientCreateDTO} to {@link ClientEntity}</li>
 *     <li>Update an existing {@link ClientEntity} from a {@link ClientUpdateDTO}</li>
 *     <li>Convert a {@link ClientEntity} to {@link ClientResponseDTO}</li>
 * </ul>
 * </p>
 *
 * author Angel Lomelí
 */
@NoArgsConstructor
public class ClientMapper {

    /**
     * Converts a {@link ClientCreateDTO} to a {@link ClientEntity}.
     *
     * @param dto the client creation DTO
     * @return the corresponding ClientEntity, or null if the DTO is null
     */
    public static ClientEntity toEntity(ClientCreateDTO dto){
        if(dto == null) return null;

        return ClientEntity.builder()
                .name(dto.name())
                .age(dto.age())
                .email(dto.email())
                .address(dto.address())
                .build();
    }

    /**
     * Updates an existing {@link ClientEntity} with values from a {@link ClientUpdateDTO}.
     *
     * @param entity the client entity to update
     * @param dto    the client update DTO
     */
    public static void updateEntity(ClientEntity entity, ClientUpdateDTO dto){
        if(dto == null) return;

        if(dto.name() != null) entity.setName(dto.name());
        if(dto.age() != null) entity.setAge(dto.age());
        if(dto.email() != null) entity.setEmail(dto.email());
        if(dto.address() != null) entity.setAddress(dto.address());
    }

    /**
     * Converts a {@link ClientEntity} to a {@link ClientResponseDTO}.
     *
     * @param entity the client entity
     * @return the corresponding ClientResponseDTO, or null if the entity is null
     */
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
