package com.pikolic.meli.repository;

import com.pikolic.meli.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for accessing {@link OrderEntity} data.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations
 * for orders, such as save, findById, findAll, delete, etc.
 * </p>
 *
 * <p>
 * Additional query methods:
 * <ul>
 *     <li>{@link #findOrdersByClientId(Long)}: retrieves all orders for a given client ID</li>
 * </ul>
 * </p>
 *
 * author Angel Lomelí
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    /**
     * Finds all orders associated with a specific client.
     *
     * @param clientId the ID of the client
     * @return list of orders for the given client
     */
    List<OrderEntity> findOrdersByClientId(Long clientId);
}
