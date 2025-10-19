package com.pikolic.meli.repository;

import com.pikolic.meli.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
