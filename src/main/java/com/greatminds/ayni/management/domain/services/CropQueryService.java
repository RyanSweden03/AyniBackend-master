package com.greatminds.ayni.management.domain.services;

import com.greatminds.ayni.management.domain.model.aggregates.Crop;
import com.greatminds.ayni.management.domain.model.queries.GetAllCropsQuery;
import com.greatminds.ayni.management.domain.model.queries.GetAllCropsByProductIdQuery;
import com.greatminds.ayni.management.domain.model.queries.GetCropByIdQuery;
import com.greatminds.ayni.management.domain.model.queries.GetCropByProductIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service for handling crop queries.
 */
public interface CropQueryService {
    List<Crop> handle(GetAllCropsQuery query);
    Optional<Crop> handle(GetCropByIdQuery query);
    List<Crop> handle(GetAllCropsByProductIdQuery query);
    Optional<Crop> handle(GetCropByProductIdQuery query);
}
