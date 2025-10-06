package com.greatminds.ayni.management.domain.model.commands;

public record CreateProductCommand(String name, String description, String recommendedCultivationDistance, String recommendedCultivationDepth,
                                   String recommendedGrowingClimate, String recommendedSoilType, String recommendedGrowingSeason, String imageUrl,
                                   Long userId) {
}
