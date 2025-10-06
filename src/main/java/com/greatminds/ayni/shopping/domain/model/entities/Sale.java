package com.greatminds.ayni.shopping.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * Represents the sale entity
 * The structure of the "sales" table in the database is mapped.
 */
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private Double unitPrice;

    @Getter
    private Long quantity;

    @Getter
    private String imageUrl;

    @Getter
    private Long userId;

    public Sale() {
    }

    public Sale(String name, String description, Double unitPrice, Long quantity, String imageUrl, Long userId) {
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }
}
