package com.greatminds.ayni.shopping.domain.model.commands;

public record CreateSaleCommand(String name, String description, Double unitPrice, Long quantity, String imageUrl, Long userId) {
}
