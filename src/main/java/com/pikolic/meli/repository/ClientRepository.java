package com.pikolic.meli.repository;

import com.pikolic.meli.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
