package com.pikolic.meli.repository;

import com.pikolic.meli.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing {@link ClientEntity} data.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations
 * for clients, such as save, findById, findAll, delete, etc.
 * </p>
 *
 * author Angel Lomelí
 */
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
