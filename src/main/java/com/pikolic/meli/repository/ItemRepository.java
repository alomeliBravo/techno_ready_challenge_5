package com.pikolic.meli.repository;

import com.pikolic.meli.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing {@link ItemEntity} data.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations
 * for items, such as save, findById, findAll, delete, etc.
 * </p>
 *
 * author Angel Lomelí
 */
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
