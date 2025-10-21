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

@Service
@RequiredArgsConstructor
public class ClientOrderServiceImpl implements ClientOrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<OrderResponseDTO> getOrdersByClientId(Long clientId){
        ClientEntity client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("No Client found with id " + clientId));

        List<OrderEntity> clientOrders = this.orderRepository.findOrdersByClientId(clientId);

        return clientOrders.stream().map(OrderMapper::toResponse).toList();
    }

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
