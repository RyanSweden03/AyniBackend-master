package com.greatminds.ayni.shopping.interfaces.rest.transform;

import com.greatminds.ayni.shopping.domain.model.commands.CreateOrderCommand;
import com.greatminds.ayni.shopping.interfaces.rest.resources.CreateOrderResource;

public class CreateOrderCommandFromResourceAssembler {
    public static CreateOrderCommand toCommandFromResource(CreateOrderResource resource){
        return new CreateOrderCommand(resource.description(), resource.totalPrice(), resource.quantity(), resource.paymentMethod(), resource.saleId(), resource.orderedBy(), resource.acceptedBy(), resource.orderedDate());
    }
}
