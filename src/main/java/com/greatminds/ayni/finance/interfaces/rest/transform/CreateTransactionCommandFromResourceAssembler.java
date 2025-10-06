package com.greatminds.ayni.finance.interfaces.rest.transform;

import com.greatminds.ayni.finance.domain.model.commands.CreateTransactionCommand;
import com.greatminds.ayni.finance.interfaces.rest.resources.CreateTransactionResource;

public class CreateTransactionCommandFromResourceAssembler {
    public static CreateTransactionCommand toCommandFromResource(CreateTransactionResource resource){
        return new CreateTransactionCommand(resource.costName(), resource.description(), resource.date(), resource.transactionType(),
                resource.price(), resource.quantity(), resource.userId());
    }
}
