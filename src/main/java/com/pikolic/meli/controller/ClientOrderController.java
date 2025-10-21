package com.pikolic.meli.controller;

import com.pikolic.meli.dto.order.OrderCreateForClientDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;
import com.pikolic.meli.service.ClientOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
class ClientOrderController {

    private final ClientOrderService clientOrderService;

    @GetMapping({"/{clientId}/orders","/{clientId}/orders/"})
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByClientId(@PathVariable Long clientId){
        return ResponseEntity.ok(this.clientOrderService.getOrdersByClientId(clientId));
    }

    @GetMapping("/{clientId}/orders/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderByClientAndId(@PathVariable Long clientId, @PathVariable Long orderId){
        return ResponseEntity.ok(this.clientOrderService.getOrderByClientAndId(clientId, orderId));
    }

    @PostMapping({"/{clientId}/orders","/{clientId}/orders/"})
    public ResponseEntity<OrderResponseDTO> createOrderForClient(@PathVariable Long clientId, @Valid @RequestBody OrderCreateForClientDTO dto){
        return ResponseEntity.ok(this.clientOrderService.createOrderForClient(clientId, dto));
    }

    @PutMapping("/{clientId}/orders/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrderByClientAndOrderId(@PathVariable Long clientId, @PathVariable Long orderId, @Valid @RequestBody OrderCreateForClientDTO dto){
        return ResponseEntity.ok(this.clientOrderService.updateOrderById(clientId, orderId, dto));
    }

    @DeleteMapping("/{clientId}/orders/{orderId}")
    public ResponseEntity<Void> deleteOrderByClientAndOrderId(@PathVariable Long clientId, @PathVariable Long orderId){
        this.clientOrderService.deleteOrderById(clientId, orderId);
        return ResponseEntity.noContent().build();
    }
}
