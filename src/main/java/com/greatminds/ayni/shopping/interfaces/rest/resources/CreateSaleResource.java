package com.greatminds.ayni.shopping.interfaces.rest.resources;

public record CreateSaleResource(String name, String description, Double unitPrice, Long quantity, String imageUrl, Long userId) {
}
