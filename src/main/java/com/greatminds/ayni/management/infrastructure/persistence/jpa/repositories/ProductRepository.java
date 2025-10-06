package com.greatminds.ayni.management.infrastructure.persistence.jpa.repositories;

import com.greatminds.ayni.management.domain.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
