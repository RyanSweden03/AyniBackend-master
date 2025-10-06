package com.greatminds.ayni.management.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProductId(Long productId) {

    public ProductId() {
        this(0L);
    }

    public ProductId {
        if (productId < 0) {
            throw new IllegalArgumentException("Product id cannot be negative");
        }
    }

    public Long GetId() {
        return productId;
    }
}
