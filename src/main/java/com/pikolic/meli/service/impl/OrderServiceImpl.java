package com.pikolic.meli.service.impl;

import com.pikolic.meli.dto.order.OrderCreateDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;
import com.pikolic.meli.dto.order.OrderUpdateDTO;
import com.pikolic.meli.entity.ClientEntity;
import com.pikolic.meli.entity.ItemEntity;
import com.pikolic.meli.entity.OrderEntity;
import com.pikolic.meli.exception.NotFoundException;
import com.pikolic.meli.mapper.OrderMapper;
import com.pikolic.meli.repository.ClientRepository;
import com.pikolic.meli.repository.ItemRepository;
import com.pikolic.meli.repository.OrderRepository;
import com.pikolic.meli.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ItemRepository itemRepository;

    @Override
    public OrderResponseDTO create(@Valid OrderCreateDTO dto){
        ClientEntity client = clientRepository.findById(dto.clientId())
                .orElseThrow(()->new NotFoundException("No Client found with id " + dto.clientId()));

        ItemEntity item = itemRepository.findById(dto.itemId())
                .orElseThrow(()->new NotFoundException("No Item found with id " + dto.itemId()));

        OrderEntity order = OrderMapper.toEntity(dto, client, item);
        this.orderRepository.save(order);

        return OrderMapper.toResponse(order);
    }

    @Override
    public OrderResponseDTO getById(Long id){
        OrderEntity order = this.orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No Order found with id " + id));

        return OrderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponseDTO> getAll(){
        List<OrderEntity> orders = this.orderRepository.findAll();

        if(orders.isEmpty()){
            throw new NotFoundException("No orders found");
        }

        return orders.stream().map(OrderMapper::toResponse).toList();
    }

    @Override
    public OrderResponseDTO update(Long id, @Valid OrderUpdateDTO dto){
        OrderEntity order = this.orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No Order found with id " + id));

        ClientEntity client = this.clientRepository.findById(dto.clientId())
                .orElseThrow(()->new NotFoundException("No Client found with id " + dto.clientId()));

        ItemEntity item = this.itemRepository.findById(dto.itemId())
                .orElseThrow(()->new NotFoundException("No Item found with id " + dto.itemId()));


        OrderMapper.updateEntity(order, dto, client, item);
        this.orderRepository.save(order);

        return OrderMapper.toResponse(order);
    }

    @Override
    public void delete(Long id){
        OrderEntity order = this.orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No Order found with id " + id));

        this.orderRepository.delete(order);
    }
}