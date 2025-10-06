package com.greatminds.ayni.finance.domain.services;

import com.greatminds.ayni.finance.domain.model.aggregates.Transaction;
import com.greatminds.ayni.finance.domain.model.commands.CreateTransactionCommand;
import com.greatminds.ayni.finance.domain.model.commands.DeleteTransactionCommand;
import com.greatminds.ayni.finance.domain.model.commands.UpdateTransactionCommand;

import java.util.Optional;

public interface TransactionCommandService {
    Long handle(CreateTransactionCommand command);

    void handle(DeleteTransactionCommand command);
    Optional<Transaction> handle(UpdateTransactionCommand command);
}
