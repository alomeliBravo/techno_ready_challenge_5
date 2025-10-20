package com.pikolic.meli.controller;

import com.pikolic.meli.dto.order.OrderCreateDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;
import com.pikolic.meli.dto.order.OrderUpdateDTO;
import com.pikolic.meli.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
class OrderController {

    private final OrderService orderService;

    @PutMapping({"","/"})
    public ResponseEntity<OrderResponseDTO> addOrder(@Valid @RequestBody OrderCreateDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.orderService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getByItemId(@PathVariable Long id){
        return ResponseEntity.ok(this.orderService.getById(id));
    }

    @GetMapping({"","/"})
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrderById(@PathVariable Long id, @Valid @RequestBody OrderUpdateDTO dto){
        return ResponseEntity.ok(this.orderService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id){
        this.orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
