package com.pikolic.meli.mapper;

import com.pikolic.meli.dto.order.OrderCreateDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;
import com.pikolic.meli.dto.order.OrderUpdateDTO;
import com.pikolic.meli.entity.ClientEntity;
import com.pikolic.meli.entity.ItemEntity;
import com.pikolic.meli.entity.OrderEntity;
import lombok.NoArgsConstructor;

/**
 * Mapper class for converting between {@link OrderEntity} and order DTOs.
 * <p>
 * Provides static methods to:
 * <ul>
 *     <li>Convert a {@link OrderCreateDTO} with associated client and item to {@link OrderEntity}</li>
 *     <li>Update an existing {@link OrderEntity} using {@link OrderUpdateDTO} and optional client/item entities</li>
 *     <li>Convert a {@link OrderEntity} to {@link OrderResponseDTO}</li>
 * </ul>
 * </p>
 *
 * author Angel Lomelí
 */
@NoArgsConstructor
public class OrderMapper {

    /**
     * Converts a {@link OrderCreateDTO} along with {@link ClientEntity} and {@link ItemEntity}
     * to a new {@link OrderEntity}.
     *
     * @param dto    the order creation DTO
     * @param client the client associated with the order
     * @param item   the item associated with the order
     * @return the corresponding OrderEntity, or null if any argument is null
     */
    public static OrderEntity toEntity(OrderCreateDTO dto, ClientEntity client, ItemEntity item) {
        if(dto == null || client == null || item == null ) return null;

        return OrderEntity.builder()
                .client(client)
                .item(item)
                .purchaseDate(dto.purchaseDate())
                .total(dto.total())
                .build();
    }

    /**
     * Updates an existing {@link OrderEntity} with values from {@link OrderUpdateDTO}
     * and optional client and item entities.
     *
     * @param entity the order entity to update
     * @param dto    the order update DTO
     * @param client the client entity (optional)
     * @param item   the item entity (optional)
     */
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

    /**
     * Converts a {@link OrderEntity} to a {@link OrderResponseDTO}.
     *
     * @param entity the order entity
     * @return the corresponding OrderResponseDTO, or null if the entity is null
     */
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
