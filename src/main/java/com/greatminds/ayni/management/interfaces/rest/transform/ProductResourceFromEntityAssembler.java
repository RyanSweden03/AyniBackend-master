package com.greatminds.ayni.management.interfaces.rest.transform;

import com.greatminds.ayni.management.domain.model.entities.Product;
import com.greatminds.ayni.management.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity){
        return new ProductResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getRecommendedCultivationDistance(),
                entity.getRecommendedCultivationDepth(),
                entity.getRecommendedGrowingClimate(),
                entity.getRecommendedSoilType(),
                entity.getRecommendedGrowingSeason(),
                entity.getImageUrl(),
                entity.getUserId()
        );
    }
}
