package com.greatminds.ayni.management.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * Represents product entity
 * Maps the structure of the "products" table in the database.
 */
@Entity
@Table(name="products")
public class Product {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private String recommendedCultivationDistance;

    @Getter
    private String recommendedCultivationDepth;

    @Getter
    private String recommendedGrowingClimate;

    @Getter
    private String recommendedSoilType;

    @Getter
    private String recommendedGrowingSeason;

    @Getter
    private String imageUrl;

    @Getter
    private Long userId;

    public Product() {
    }

    public Product(String name, String description, String recommendedCultivationDistance, String recommendedCultivationDepth,
                   String recommendedGrowingClimate, String recommendedSoilType, String recommendedGrowingSeason, String imageUrl,
                   Long userId) {
        this.name = name;
        this.description = description;
        this.recommendedCultivationDistance = recommendedCultivationDistance;
        this.recommendedCultivationDepth = recommendedCultivationDepth;
        this.recommendedGrowingClimate = recommendedGrowingClimate;
        this.recommendedSoilType = recommendedSoilType;
        this.recommendedGrowingSeason = recommendedGrowingSeason;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }
}
