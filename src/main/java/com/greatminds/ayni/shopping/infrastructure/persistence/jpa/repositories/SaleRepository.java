package com.greatminds.ayni.shopping.infrastructure.persistence.jpa.repositories;

import com.greatminds.ayni.shopping.domain.model.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
