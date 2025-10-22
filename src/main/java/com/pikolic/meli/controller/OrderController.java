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

/**
 * REST controller for managing orders in the Meli e-commerce API.
 * <p>
 * This controller provides endpoints to create, retrieve, update, and delete orders.
 * All business logic is delegated to the {@link OrderService}.
 * </p>
 *
 * <p><b>Base path:</b> {@code /api/v1/orders}</p>
 *
 * <p>Example endpoints:</p>
 * <ul>
 *     <li>{@code POST /api/v1/orders} – Create a new order</li>
 *     <li>{@code GET /api/v1/orders} – Retrieve all orders</li>
 *     <li>{@code GET /api/v1/orders/{id}} – Retrieve a specific order by ID</li>
 *     <li>{@code PUT /api/v1/orders/{id}} – Update an existing order</li>
 *     <li>{@code DELETE /api/v1/orders/{id}} – Delete an order by ID</li>
 * </ul>
 *
 * <p>All endpoints return {@link ResponseEntity} objects with appropriate HTTP status codes.</p>
 *
 * @author Angel Lomelí
 * @see OrderService
 * @see OrderCreateDTO
 * @see OrderUpdateDTO
 * @see OrderResponseDTO
 */
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
class OrderController {

    /** Service layer responsible for handling order-related operations. */
    private final OrderService orderService;

    /**
     * Creates a new order.
     *
     * @param dto the data transfer object containing the new order details
     * @return a {@link ResponseEntity} containing the created {@link OrderResponseDTO}
     *         and an HTTP 201 (Created) status
     * @throws jakarta.validation.ConstraintViolationException if validation on {@code dto} fails
     */
    @PostMapping({"", "/"})
    public ResponseEntity<OrderResponseDTO> addOrder(@Valid @RequestBody OrderCreateDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.orderService.create(dto));
    }

    /**
     * Retrieves a specific order by its unique ID.
     *
     * @param id the ID of the order to retrieve
     * @return a {@link ResponseEntity} containing the requested {@link OrderResponseDTO}
     *         and an HTTP 200 (OK) status
     * @throws com.pikolic.meli.exception.NotFoundException if the order does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getByItemId(@PathVariable Long id) {
        return ResponseEntity.ok(this.orderService.getById(id));
    }

    /**
     * Retrieves all orders.
     *
     * @return a {@link ResponseEntity} containing a list of {@link OrderResponseDTO}
     *         and an HTTP 200 (OK) status
     */
    @GetMapping({"", "/"})
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(this.orderService.getAll());
    }

    /**
     * Updates an existing order by its unique ID.
     *
     * @param id  the ID of the order to update
     * @param dto the data transfer object containing the updated order details
     * @return a {@link ResponseEntity} containing the updated {@link OrderResponseDTO}
     *         and an HTTP 200 (OK) status
     * @throws com.pikolic.meli.exception.NotFoundException if the order does not exist
     * @throws jakarta.validation.ConstraintViolationException if validation on {@code dto} fails
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrderById(@PathVariable Long id, @Valid @RequestBody OrderUpdateDTO dto) {
        return ResponseEntity.ok(this.orderService.update(id, dto));
    }

    /**
     * Deletes an order by its unique ID.
     *
     * @param id the ID of the order to delete
     * @return a {@link ResponseEntity} with an HTTP 204 (No Content) status if deletion is successful
     * @throws com.pikolic.meli.exception.NotFoundException if the order does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        this.orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
