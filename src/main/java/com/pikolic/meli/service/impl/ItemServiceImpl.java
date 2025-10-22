package com.pikolic.meli.service.impl;

import com.pikolic.meli.dto.item.ItemCreateDTO;
import com.pikolic.meli.dto.item.ItemResponseDTO;
import com.pikolic.meli.dto.item.ItemUpdateDTO;
import com.pikolic.meli.entity.ItemEntity;
import com.pikolic.meli.exception.NotFoundException;
import com.pikolic.meli.mapper.ItemMapper;
import com.pikolic.meli.repository.ItemRepository;
import com.pikolic.meli.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link ItemService} for managing items.
 * <p>
 * Provides methods to create, retrieve, update, and delete items.
 * Uses {@link ItemMapper} to convert between DTOs and {@link ItemEntity}.
 * </p>
 *
 * author Angel Lomelí
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    /**
     * Creates a new item.
     *
     * @param dto the item creation DTO
     * @return the created item response
     */
    @Override
    public ItemResponseDTO create(@Valid ItemCreateDTO dto){
        ItemEntity itemEntity = ItemMapper.toEntity(dto);
        this.itemRepository.save(itemEntity);
        return ItemMapper.toResponse(itemEntity);
    }

    /**
     * Retrieves an item by its ID.
     *
     * @param id the ID of the item
     * @return the item response
     * @throws NotFoundException if the item does not exist
     */
    @Override
    public ItemResponseDTO getById(Long id){
        ItemEntity item = this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + id));

        return ItemMapper.toResponse(item);
    }

    /**
     * Retrieves all items.
     *
     * @return list of item responses
     * @throws NotFoundException if no items exist
     */
    @Override
    public List<ItemResponseDTO> getAll(){
        List<ItemEntity> items = this.itemRepository.findAll();

        if(items.isEmpty()){
            throw new NotFoundException("No items found");
        }

        return items.stream().map(ItemMapper::toResponse).toList();
    }

    /**
     * Updates an existing item.
     *
     * @param id  the ID of the item
     * @param dto the item update DTO
     * @return the updated item response
     * @throws NotFoundException if the item does not exist
     */
    @Override
    public ItemResponseDTO update(Long id, @Valid ItemUpdateDTO dto){
        ItemEntity item = this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + id));

        ItemMapper.updateEntity(item, dto);
        ItemEntity itemUpdated = this.itemRepository.save(item);
        return ItemMapper.toResponse(itemUpdated);
    }

    /**
     * Deletes an item by its ID.
     *
     * @param id the ID of the item
     * @throws NotFoundException if the item does not exist
     */
    @Override
    public void delete(Long id){
        ItemEntity item = this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + id));

        this.itemRepository.delete(item);
    }
}
