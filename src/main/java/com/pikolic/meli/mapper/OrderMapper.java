package com.pikolic.meli.mapper;

import com.pikolic.meli.dto.order.OrderCreateDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;
import com.pikolic.meli.dto.order.OrderUpdateDTO;
import com.pikolic.meli.entity.ClientEntity;
import com.pikolic.meli.entity.ItemEntity;
import com.pikolic.meli.entity.OrderEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderMapper {

    public static OrderEntity toEntity(OrderCreateDTO dto, ClientEntity client, ItemEntity item) {
        if(dto == null || client == null || item == null ) return null;

        return OrderEntity.builder()
                .client(client)
                .item(item)
                .purchaseDate(dto.purchaseDate())
                .total(dto.total())
                .build();
    }

    public static void updateEntity(OrderEntity entity, OrderUpdateDTO dto, ClientEntity client, ItemEntity item){
        if (dto == null) return;

        if(dto.clientId() != null && client != null) {
            entity.setClient(client);
        }

        if(dto.itemId() != null && item != null) {
            entity.setItem(item);
        }

        if(dto.purchaseDate() != null) {
            entity.setPurchaseDate(dto.purchaseDate());
        }

        if(dto.total() != null) {
            entity.setTotal(dto.total());
        }
    }

    public static OrderResponseDTO toResponse(OrderEntity entity){
        if(entity == null) return null;

        return new OrderResponseDTO(
                entity.getId(),
                entity.getClient().getId(),
                entity.getItem().getId(),
                entity.getPurchaseDate(),
                entity.getTotal()
        );
    }
}
