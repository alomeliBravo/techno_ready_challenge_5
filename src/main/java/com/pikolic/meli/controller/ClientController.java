package com.pikolic.meli.controller;

import com.pikolic.meli.dto.client.ClientCreateDTO;
import com.pikolic.meli.dto.client.ClientResponseDTO;
import com.pikolic.meli.dto.client.ClientUpdateDTO;
import com.pikolic.meli.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping({"","/"})
    public ResponseEntity<ClientResponseDTO> addClient(@Valid @RequestBody ClientCreateDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.clientService.create(dto));
    }

    @GetMapping({"","/"})
    public ResponseEntity<List<ClientResponseDTO>> getAll(){
        return ResponseEntity.ok(this.clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.clientService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientUpdateDTO dto){
        return ResponseEntity.ok(this.clientService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
