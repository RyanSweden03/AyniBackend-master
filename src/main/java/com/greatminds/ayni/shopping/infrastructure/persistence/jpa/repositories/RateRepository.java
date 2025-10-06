package com.greatminds.ayni.shopping.infrastructure.persistence.jpa.repositories;

import com.greatminds.ayni.shopping.domain.model.aggregates.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {
}
