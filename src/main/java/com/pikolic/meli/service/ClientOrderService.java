package com.pikolic.meli.service;

import com.pikolic.meli.dto.order.OrderCreateForClientDTO;
import com.pikolic.meli.dto.order.OrderResponseDTO;

import java.util.List;

public interface ClientOrderService {
    List<OrderResponseDTO> getOrdersByClientId(Long clientId);
    OrderResponseDTO getOrderByClientAndId(Long clientId, Long orderId);
    OrderResponseDTO createOrderForClient(Long clientId, OrderCreateForClientDTO dto);
    OrderResponseDTO updateOrderById(Long clientId, Long orderId, OrderCreateForClientDTO dto);
    void deleteOrderById(Long clientId, Long orderId);
}
