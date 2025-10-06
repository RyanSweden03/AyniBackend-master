package com.greatminds.ayni.shopping.interfaces.rest.transform;


import com.greatminds.ayni.shopping.domain.model.commands.CreateRateCommand;
import com.greatminds.ayni.shopping.interfaces.rest.resources.CreateRateResource;

public class CreateRateCommandFromResourceAssembler {
    public static CreateRateCommand toCommandFromResource(CreateRateResource resource){
        return new CreateRateCommand(resource.rate(), resource.date(), resource.productId(), resource.userId());
    }
}
