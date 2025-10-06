package com.greatminds.ayni.shopping.interfaces.rest.transform;

import com.greatminds.ayni.shopping.domain.model.aggregates.Order;
import com.greatminds.ayni.shopping.interfaces.rest.resources.OrderResource;

public class OrderResourceFromEntityAssembler {
    public static OrderResource toResourceFromEntity(Order entity) {
        return new OrderResource(
                entity.getId(),
                entity.getDescription(),
                entity.getTotalPrice(),
                entity.getQuantity(),
                entity.getPaymentMethod(),
                entity.getSale().getId(),
                entity.getOrderedBy(),
                entity.getAcceptedBy(),
                entity.getOrderedDate(),
                entity.getStatus()
        );
    }
}