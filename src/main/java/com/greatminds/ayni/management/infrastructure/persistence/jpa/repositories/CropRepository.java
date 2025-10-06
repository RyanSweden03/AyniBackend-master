package com.greatminds.ayni.management.infrastructure.persistence.jpa.repositories;

import com.greatminds.ayni.management.domain.model.aggregates.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CropRepository extends JpaRepository<Crop, Long> {
    Optional<Crop> findCropByProductIdAndId(Long productId, Long cropId);
    List<Crop> findAllByProductId(Long productId);
}
