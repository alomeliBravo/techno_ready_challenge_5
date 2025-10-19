package com.pikolic.meli.service;

import com.pikolic.meli.dto.item.ItemCreateDTO;
import com.pikolic.meli.dto.item.ItemResponseDTO;
import com.pikolic.meli.dto.item.ItemUpdateDTO;

import java.util.List;

public interface ItemService {
    ItemResponseDTO create(ItemCreateDTO dto);
    ItemResponseDTO getById(Long id);
    List<ItemResponseDTO> getAll();
    ItemResponseDTO update(Long id, ItemUpdateDTO dto);
    void delete(Long id);
}
