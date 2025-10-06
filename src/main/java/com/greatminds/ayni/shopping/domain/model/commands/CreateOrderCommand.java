package com.greatminds.ayni.shopping.domain.model.commands;

import java.util.Date;

public record CreateOrderCommand(String description, Double totalPrice, Long quantity,  String paymentMethod, Long saleId, Long orderedBy, Long acceptedBy, Date orderedDate) {

}