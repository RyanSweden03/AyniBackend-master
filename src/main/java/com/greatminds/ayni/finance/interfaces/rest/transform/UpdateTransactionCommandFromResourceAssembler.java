package com.greatminds.ayni.finance.interfaces.rest.transform;

import com.greatminds.ayni.finance.domain.model.commands.UpdateTransactionCommand;
import com.greatminds.ayni.finance.interfaces.rest.resources.UpdateTransactionResource;

public class UpdateTransactionCommandFromResourceAssembler {
    public static UpdateTransactionCommand toCommandFromResource(Long courseId, UpdateTransactionResource resource) {
        return new UpdateTransactionCommand(courseId, resource.costName(), resource.description(), resource.date(),
                resource.transactionType(), resource.price(), resource.quantity(), resource.userId());
    }
}
