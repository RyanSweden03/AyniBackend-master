package com.greatminds.ayni.finance.interfaces.rest.transform;

import com.greatminds.ayni.finance.domain.model.aggregates.Transaction;
import com.greatminds.ayni.finance.interfaces.rest.resources.TransactionResource;

public class TransactionResourceFromEntityAssembler {

    public static TransactionResource toResourceFromEntity(Transaction entity) {
        return new TransactionResource(entity.getId(), entity.getCostName(), entity.getDate(), entity.getDescription(),
                entity.getTransactionType(), entity.getPrice(), entity.getQuantity(), entity.getUserId());
    }
}
