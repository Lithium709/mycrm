package com.enterprise.data.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.data.entities.Order;

public interface OrderRepository extends JpaRepository<Order, UUID>{

}
