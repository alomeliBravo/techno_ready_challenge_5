package com.pikolic.meli.mapper;

import com.pikolic.meli.dto.item.ItemCreateDTO;
import com.pikolic.meli.dto.item.ItemResponseDTO;
import com.pikolic.meli.dto.item.ItemUpdateDTO;
import com.pikolic.meli.entity.ItemEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemMapper {
    public static ItemEntity toEntity(ItemCreateDTO dto){
        if(dto == null) return null;

        return ItemEntity.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .build();
    }

    public static void updateEntity(ItemEntity entity, ItemUpdateDTO dto){
        if(dto == null) return;

        if(dto.name() != null) entity.setName(dto.name());
        if(dto.description() != null) entity.setDescription(dto.description());
        if(dto.price() != null) entity.setPrice(dto.price());
    }

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
