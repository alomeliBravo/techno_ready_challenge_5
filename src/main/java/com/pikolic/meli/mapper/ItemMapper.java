package com.pikolic.meli.mapper;

import com.pikolic.meli.dto.item.ItemCreateDTO;
import com.pikolic.meli.dto.item.ItemResponseDTO;
import com.pikolic.meli.dto.item.ItemUpdateDTO;
import com.pikolic.meli.entity.ItemEntity;
import lombok.NoArgsConstructor;

/**
 * Mapper class for converting between {@link ItemEntity} and item DTOs.
 * <p>
 * Provides static methods to:
 * <ul>
 *     <li>Convert a {@link ItemCreateDTO} to {@link ItemEntity}</li>
 *     <li>Update an existing {@link ItemEntity} from a {@link ItemUpdateDTO}</li>
 *     <li>Convert a {@link ItemEntity} to {@link ItemResponseDTO}</li>
 * </ul>
 * </p>
 *
 * author Angel Lomelí
 */
@NoArgsConstructor
public class ItemMapper {

    /**
     * Converts a {@link ItemCreateDTO} to a {@link ItemEntity}.
     *
     * @param dto the item creation DTO
     * @return the corresponding ItemEntity, or null if the DTO is null
     */
    public static ItemEntity toEntity(ItemCreateDTO dto){
        if(dto == null) return null;

        return ItemEntity.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .build();
    }

    /**
     * Updates an existing {@link ItemEntity} with values from a {@link ItemUpdateDTO}.
     *
     * @param entity the item entity to update
     * @param dto    the item update DTO
     */
    public static void updateEntity(ItemEntity entity, ItemUpdateDTO dto){
        if(dto == null) return;

        if(dto.name() != null) entity.setName(dto.name());
        if(dto.description() != null) entity.setDescription(dto.description());
        if(dto.price() != null) entity.setPrice(dto.price());
    }

    /**
     * Converts a {@link ItemEntity} to a {@link ItemResponseDTO}.
     *
     * @param entity the item entity
     * @return the corresponding ItemResponseDTO, or null if the entity is null
     */
    public static ItemResponseDTO toResponse(ItemEntity entity){
        if(entity == null) return null;

        return new ItemResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
