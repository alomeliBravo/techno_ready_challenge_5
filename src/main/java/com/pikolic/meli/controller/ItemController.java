package com.pikolic.meli.controller;

import com.pikolic.meli.dto.item.ItemCreateDTO;
import com.pikolic.meli.dto.item.ItemResponseDTO;
import com.pikolic.meli.dto.item.ItemUpdateDTO;
import com.pikolic.meli.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
class ItemController {

    private final ItemService itemService;

    @PostMapping({"","/"})
    public ResponseEntity<ItemResponseDTO> addItem(@Valid @RequestBody ItemCreateDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.itemService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id){
        return ResponseEntity.ok(this.itemService.getById(id));
    }

    @GetMapping({"","/"})
    public ResponseEntity<List<ItemResponseDTO>> getAllItems(){
        return ResponseEntity.ok(this.itemService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItemById(@PathVariable Long id, @Valid @RequestBody ItemUpdateDTO dto){
        return ResponseEntity.ok(this.itemService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable Long id){
        this.itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
