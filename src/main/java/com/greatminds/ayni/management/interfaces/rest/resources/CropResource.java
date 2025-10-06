package com.greatminds.ayni.management.interfaces.rest.resources;

import com.greatminds.ayni.management.domain.model.entities.Product;

public record CropResource(Long id, String name, Boolean pickUpWeed, Boolean fertilizeCrop, Boolean oxygenateCrop, Boolean makeCropLine,
                           Boolean makeCropHole, Long wateringDays, Long pestCleanupDays, Long productId, Long userId) {
}
