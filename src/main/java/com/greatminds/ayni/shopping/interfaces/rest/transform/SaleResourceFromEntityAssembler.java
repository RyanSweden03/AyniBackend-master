package com.greatminds.ayni.shopping.interfaces.rest.transform;

import com.greatminds.ayni.shopping.domain.model.entities.Sale;
import com.greatminds.ayni.shopping.interfaces.rest.resources.SaleResource;

public class SaleResourceFromEntityAssembler {
    public static SaleResource toResourceFromEntity(Sale entity) {
        return new SaleResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getUnitPrice(), entity.getQuantity(), entity.getImageUrl(), entity.getUserId());
    }
}
