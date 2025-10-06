package com.greatminds.ayni.management.interfaces.rest.resources;

public record CreateProductResource(String name, String description, String recommendedCultivationDistance, String recommendedCultivationDepth,
                                    String recommendedGrowingClimate, String recommendedSoilType, String recommendedGrowingSeason, String imageUrl,
                                    Long userId) {

}
