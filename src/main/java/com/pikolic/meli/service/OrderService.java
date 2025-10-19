package com.pikolic.meli.service;

import com.pikolic.meli.dto.order.OrderCreateDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;
import com.pikolic.meli.dto.order.OrderUpdateDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO create(OrderCreateDTO dto);
    OrderResponseDTO getById(Long id);
    List<OrderResponseDTO> getAll();
    OrderResponseDTO update(Long id, OrderUpdateDTO dto);
    void delete(Long id);
}
