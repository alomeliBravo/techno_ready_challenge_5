package com.pikolic.meli.repository;

import com.pikolic.meli.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
