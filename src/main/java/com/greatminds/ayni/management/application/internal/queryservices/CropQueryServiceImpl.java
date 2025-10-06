package com.greatminds.ayni.management.application.internal.queryservices;

import com.greatminds.ayni.management.domain.model.aggregates.Crop;
import com.greatminds.ayni.management.domain.model.queries.GetAllCropsByProductIdQuery;
import com.greatminds.ayni.management.domain.model.queries.GetAllCropsQuery;
import com.greatminds.ayni.management.domain.model.queries.GetCropByIdQuery;
import com.greatminds.ayni.management.domain.model.queries.GetCropByProductIdQuery;
import com.greatminds.ayni.management.domain.services.CropQueryService;
import com.greatminds.ayni.management.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropQueryServiceImpl implements CropQueryService {
    private final CropRepository cropRepository;

    public CropQueryServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Override
    public List<Crop> handle(GetAllCropsQuery query) {
        return cropRepository.findAll();
    }

    @Override
    public Optional<Crop> handle(GetCropByIdQuery query) {
        return this.cropRepository.findById(query.id());
    }

    @Override
    public List<Crop> handle(GetAllCropsByProductIdQuery query) {
        return this.cropRepository.findAllByProductId(query.productId());
    }

    @Override
    public Optional<Crop> handle(GetCropByProductIdQuery query) {
        return this.cropRepository.findCropByProductIdAndId(query.productId(), query.cropId());
    }
}
