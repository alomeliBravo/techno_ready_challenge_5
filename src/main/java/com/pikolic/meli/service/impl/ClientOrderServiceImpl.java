package com.pikolic.meli.service.impl;

import com.pikolic.meli.dto.order.OrderCreateForClientDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;
import com.pikolic.meli.entity.ClientEntity;
import com.pikolic.meli.entity.ItemEntity;
import com.pikolic.meli.entity.OrderEntity;
import com.pikolic.meli.exception.ForbbidenException;
import com.pikolic.meli.exception.NotFoundException;
import com.pikolic.meli.mapper.OrderMapper;
import com.pikolic.meli.repository.ClientRepository;
import com.pikolic.meli.repository.ItemRepository;
import com.pikolic.meli.repository.OrderRepository;
import com.pikolic.meli.service.ClientOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of {@link ClientOrderService} for managing orders of clients.
 * <p>
 * Provides methods to create, update, retrieve, and delete orders associated with clients.
 * Validates existence of clients and items and ensures that clients can only access their own orders.
 * </p>
 *
 * author Angel Lomelí
 */
@Service
@RequiredArgsConstructor
public class ClientOrderServiceImpl implements ClientOrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ItemRepository itemRepository;

    /**
     * Retrieves all orders for a given client.
     *
     * @param clientId the ID of the client
     * @return list of order responses
     * @throws NotFoundException if the client does not exist
     */
    @Override
    public List<OrderResponseDTO> getOrdersByClientId(Long clientId){
        ClientEntity client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("No Client found with id " + clientId));

        List<OrderEntity> clientOrders = this.orderRepository.findOrdersByClientId(clientId);
        if(clientOrders.isEmpty()) throw new NotFoundException("No Orders found for client with id " + clientId);
        return clientOrders.stream().map(OrderMapper::toResponse).toList();
    }

    /**
     * Retrieves a specific order by client ID and order ID.
     *
     * @param clientId the ID of the client
     * @param orderId  the ID of the order
     * @return order response
     * @throws NotFoundException  if the client or order does not exist
     * @throws ForbbidenException if the order does not belong to the client
     */
    @Override
    public OrderResponseDTO getOrderByClientAndId(Long clientId, Long orderId){
        ClientEntity client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("No Client found with id " + clientId));

        OrderEntity order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("No Order found with id " + orderId));

        if(!order.getClient().getId().equals(clientId)){
            throw new ForbbidenException("Order with id " + orderId + " does not belong to client with id " + clientId);
        }

        return OrderMapper.toResponse(order);
    }

    /**
     * Creates a new order for a client.
     *
     * @param clientId the ID of the client
     * @param dto      the order creation DTO
     * @return the created order response
     * @throws NotFoundException if the client or item does not exist
     */
    @Override
    public OrderResponseDTO createOrderForClient(Long clientId, OrderCreateForClientDTO dto){
        ClientEntity client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("No Client found with id " + clientId));

        ItemEntity item = this.itemRepository.findById(dto.itemId())
                .orElseThrow(() -> new NotFoundException("No Item found with id " + dto.itemId()));

        OrderEntity newOrder = OrderEntity.builder()
                .client(client)
                .item(item)
                .purchaseDate(LocalDate.now())
                .total(item.getPrice())
                .build();

        this.orderRepository.save(newOrder);
        return OrderMapper.toResponse(newOrder);
    }

    /**
     * Updates an existing order for a client.
     *
     * @param clientId the ID of the client
     * @param orderId  the ID of the order
     * @param dto      the order update DTO
     * @return the updated order response
     * @throws NotFoundException  if the client, order, or item does not exist
     * @throws ForbbidenException if the order does not belong to the client
     */
    @Override
    public OrderResponseDTO updateOrderById(Long clientId, Long orderId, OrderCreateForClientDTO dto){
        OrderEntity order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("No Order found with id " + orderId));

        ClientEntity client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("No Client found with id " + clientId));

        if(!order.getClient().getId().equals(client.getId())){
            throw new ForbbidenException("Order with id " + orderId + " does not belong to client with id " + clientId);
        }

        ItemEntity item = this.itemRepository.findById(dto.itemId())
                .orElseThrow(() -> new NotFoundException("No Item found with id " + dto.itemId()));

        order.setItem(item);
        order.setPurchaseDate(LocalDate.now());
        order.setTotal(item.getPrice());
        this.orderRepository.save(order);

        return OrderMapper.toResponse(order);
    }

    /**
     * Deletes an order for a client.
     *
     * @param clientId the ID of the client
     * @param orderId  the ID of the order
     * @throws NotFoundException  if the client or order does not exist
     * @throws ForbbidenException if the order does not belong to the client
     */
    @Override
    public void deleteOrderById(Long clientId, Long orderId){
        ClientEntity client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("No Client found with id " + clientId));

        OrderEntity order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("No Order found with id " + orderId));

        if(!order.getClient().getId().equals(client.getId())){
            throw new ForbbidenException("Order with id " + orderId + " does not belong to client with id " + clientId);
        }

        this.orderRepository.delete(order);
    }
}
