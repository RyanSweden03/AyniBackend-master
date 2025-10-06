package com.greatminds.ayni.shopping.interfaces.rest.transform;

import com.greatminds.ayni.shopping.domain.model.commands.CreateSaleCommand;
import com.greatminds.ayni.shopping.interfaces.rest.resources.CreateSaleResource;

public class CreateSaleCommandFromResourceAssembler {
    public static CreateSaleCommand toCommandFromResource(CreateSaleResource resource){
        return new CreateSaleCommand(resource.name(), resource.description(), resource.unitPrice(), resource.quantity(), resource.imageUrl(), resource.userId());
    }
}
