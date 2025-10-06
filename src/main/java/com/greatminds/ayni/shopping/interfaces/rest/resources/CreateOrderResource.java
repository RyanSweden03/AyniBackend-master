package com.greatminds.ayni.shopping.interfaces.rest.resources;

import java.util.Date;

public record CreateOrderResource(String description, Double totalPrice, Long quantity, String paymentMethod, Long saleId, Long orderedBy, Long acceptedBy, Date orderedDate) {
}