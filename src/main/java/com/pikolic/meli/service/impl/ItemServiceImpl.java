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

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public ItemResponseDTO create(@Valid ItemCreateDTO dto){
        ItemEntity itemEntity = ItemMapper.toEntity(dto);
        this.itemRepository.save(itemEntity);
        return  ItemMapper.toResponse(itemEntity);
    }

    @Override
    public ItemResponseDTO getById(Long id){
        ItemEntity item = this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + id));

        return ItemMapper.toResponse(item);
    }

    @Override
    public List<ItemResponseDTO> getAll(){
        List<ItemEntity> items = this.itemRepository.findAll();

        if(items.isEmpty()){
            throw new NotFoundException("No items found");
        }

        return items.stream().map(ItemMapper::toResponse).toList();
    }

    @Override
    public ItemResponseDTO update(Long id, @Valid ItemUpdateDTO dto){
        ItemEntity item = this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + id));

        ItemMapper.updateEntity(item, dto);
        ItemEntity itemUpdated = this.itemRepository.save(item);
        return ItemMapper.toResponse(itemUpdated);
    }

    @Override
    public void delete(Long id){
        ItemEntity item = this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with id " + id));

        this.itemRepository.delete(item);
    }
}
