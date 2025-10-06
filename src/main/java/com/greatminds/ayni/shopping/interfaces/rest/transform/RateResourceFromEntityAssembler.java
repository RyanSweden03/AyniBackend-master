package com.greatminds.ayni.shopping.interfaces.rest.transform;

import com.greatminds.ayni.shopping.domain.model.aggregates.Rate;
import com.greatminds.ayni.shopping.interfaces.rest.resources.RateResource;

public class RateResourceFromEntityAssembler {
    public static RateResource toResourceFromEntity(Rate entity) {
        return new RateResource(
                entity.getId(),
                entity.getRate(),
                entity.getDate(),
                entity.getProduct().getId(),
                entity.getUserId()
        );
    }
}
