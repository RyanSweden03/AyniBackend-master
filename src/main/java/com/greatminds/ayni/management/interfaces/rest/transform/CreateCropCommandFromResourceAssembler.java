package com.greatminds.ayni.management.interfaces.rest.transform;

import com.greatminds.ayni.management.domain.model.commands.CreateCropCommand;
import com.greatminds.ayni.management.interfaces.rest.resources.CreateCropResource;

public class CreateCropCommandFromResourceAssembler {
    public static CreateCropCommand toCommandFromResource(CreateCropResource resource){
        return new CreateCropCommand(resource.name(),resource.pickUpWeed(),resource.fertilizeCrop(),resource.oxygenateCrop(),
                resource.makeCropLine(),resource.makeCropHole(),resource.wateringDays(),resource.pestCleanupDays(),
                resource.productId(), resource.userId());
    }
}
