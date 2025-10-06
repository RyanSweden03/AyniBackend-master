package com.greatminds.ayni.management.interfaces.rest.transform;

import com.greatminds.ayni.management.domain.model.aggregates.Crop;
import com.greatminds.ayni.management.interfaces.rest.resources.CropResource;

public class CropResourceFromEntityAssembler {
    public static CropResource toResourceFromEntity(Crop entity){
        return new CropResource(
                entity.getId(),
                entity.getName(),
                entity.getPickUpWeed(),
                entity.getFertilizeCrop(),
                entity.getOxygenateCrop(),
                entity.getMakeCropLine(),
                entity.getMakeCropHole(),
                entity.getWateringDays(),
                entity.getPestCleanupDays(),
                entity.getProduct().getId(),
                entity.getUserId()
        );
    }
}
