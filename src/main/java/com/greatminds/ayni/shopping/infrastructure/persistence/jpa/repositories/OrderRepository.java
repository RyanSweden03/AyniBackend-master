package com.greatminds.ayni.shopping.infrastructure.persistence.jpa.repositories;


import com.greatminds.ayni.shopping.domain.model.aggregates.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findOrderBySaleIdAndId(Long saleId, Long orderId);
}