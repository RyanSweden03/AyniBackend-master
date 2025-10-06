package com.greatminds.ayni.management.domain.model.commands;

import com.greatminds.ayni.management.domain.model.entities.Product;
import com.greatminds.ayni.management.domain.model.valueobjects.ProductId;

public record CreateCropCommand(String name, Boolean pickUpWeed, Boolean fertilizeCrop, Boolean oxygenateCrop, Boolean makeCropLine,
                                Boolean makeCropHole, Long wateringDays, Long pestCleanupDays, Long productId, Long userId) {
}
