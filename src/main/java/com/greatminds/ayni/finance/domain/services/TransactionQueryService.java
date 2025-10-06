package com.greatminds.ayni.finance.domain.services;

import com.greatminds.ayni.finance.domain.model.aggregates.Transaction;
import com.greatminds.ayni.finance.domain.model.queries.GetAllTransactionsQuery;
import com.greatminds.ayni.finance.domain.model.queries.GetTransactionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TransactionQueryService {
    Optional<Transaction> handle(GetTransactionByIdQuery query);

    List<Transaction> handle(GetAllTransactionsQuery query);
}
