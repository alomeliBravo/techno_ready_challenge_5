package com.pikolic.meli.controller;

import com.pikolic.meli.dto.order.OrderCreateForClientDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;
import com.pikolic.meli.service.ClientOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing orders associated with specific clients.
 * <p>
 * This controller exposes endpoints to create, retrieve, update, and delete
 * orders that belong to a given client. All operations are delegated to
 * the {@link ClientOrderService}.
 * </p>
 *
 * <p><b>Base path:</b> {@code /api/v1/clients}</p>
 *
 * <p>Example endpoints:</p>
 * <ul>
 *     <li>{@code GET /api/v1/clients/{clientId}/orders} – Retrieve all orders for a specific client</li>
 *     <li>{@code GET /api/v1/clients/{clientId}/orders/{orderId}} – Retrieve a specific order for a client</li>
 *     <li>{@code POST /api/v1/clients/{clientId}/orders} – Create a new order for a client</li>
 *     <li>{@code PUT /api/v1/clients/{clientId}/orders/{orderId}} – Update an existing order for a client</li>
 *     <li>{@code DELETE /api/v1/clients/{clientId}/orders/{orderId}} – Delete a specific order for a client</li>
 * </ul>
 *
 * <p>All endpoints ensure that the operations are performed only within the context of the specified client.</p>
 *
 * @author Angel Lomelí
 * @see ClientOrderService
 * @see OrderResponseDTO
 * @see OrderCreateForClientDTO
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
class ClientOrderController {

    /** Service layer responsible for handling client order operations. */
    private final ClientOrderService clientOrderService;

    /**
     * Retrieves all orders associated with a specific client.
     *
     * @param clientId the ID of the client whose orders are to be retrieved
     * @return a {@link ResponseEntity} containing a list of {@link OrderResponseDTO}
     *         and an HTTP 200 (OK) status
     * @throws com.pikolic.meli.exception.NotFoundException if the client does not exist
     */
    @GetMapping({"/{clientId}/orders", "/{clientId}/orders/"})
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(this.clientOrderService.getOrdersByClientId(clientId));
    }

    /**
     * Retrieves a specific order for a given client by order ID.
     *
     * @param clientId the ID of the client who owns the order
     * @param orderId  the ID of the order to retrieve
     * @return a {@link ResponseEntity} containing the requested {@link OrderResponseDTO}
     *         and an HTTP 200 (OK) status
     * @throws com.pikolic.meli.exception.NotFoundException if the client or order does not exist
     */
    @GetMapping("/{clientId}/orders/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderByClientAndId(@PathVariable Long clientId, @PathVariable Long orderId) {
        return ResponseEntity.ok(this.clientOrderService.getOrderByClientAndId(clientId, orderId));
    }

    /**
     * Creates a new order for a specific client.
     *
     * @param clientId the ID of the client for whom the order will be created
     * @param dto      the data transfer object containing order details
     * @return a {@link ResponseEntity} containing the created {@link OrderResponseDTO}
     *         and an HTTP 201 (Created) status
     * @throws com.pikolic.meli.exception.NotFoundException if the client does not exist
     * @throws jakarta.validation.ConstraintViolationException if validation on {@code dto} fails
     */
    @PostMapping({"/{clientId}/orders", "/{clientId}/orders/"})
    public ResponseEntity<OrderResponseDTO> createOrderForClient(@PathVariable Long clientId, @Valid @RequestBody OrderCreateForClientDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.clientOrderService.createOrderForClient(clientId, dto));
    }

    /**
     * Updates an existing order for a specific client.
     *
     * @param clientId the ID of the client who owns the order
     * @param orderId  the ID of the order to update
     * @param dto      the data transfer object containing the updated order details
     * @return a {@link ResponseEntity} containing the updated {@link OrderResponseDTO}
     *         and an HTTP 200 (OK) status
     * @throws com.pikolic.meli.exception.NotFoundException if the client or order does not exist
     * @throws jakarta.validation.ConstraintViolationException if validation on {@code dto} fails
     */
    @PutMapping("/{clientId}/orders/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrderByClientAndOrderId(
            @PathVariable Long clientId,
            @PathVariable Long orderId,
            @Valid @RequestBody OrderCreateForClientDTO dto
    ) {
        return ResponseEntity.ok(this.clientOrderService.updateOrderById(clientId, orderId, dto));
    }

    /**
     * Deletes a specific order belonging to a client.
     *
     * @param clientId the ID of the client who owns the order
     * @param orderId  the ID of the order to delete
     * @return a {@link ResponseEntity} with an HTTP 204 (No Content) status if deletion is successful
     * @throws com.pikolic.meli.exception.NotFoundException if the client or order does not exist
     */
    @DeleteMapping("/{clientId}/orders/{orderId}")
    public ResponseEntity<Void> deleteOrderByClientAndOrderId(@PathVariable Long clientId, @PathVariable Long orderId) {
        this.clientOrderService.deleteOrderById(clientId, orderId);
        return ResponseEntity.noContent().build();
    }
}
