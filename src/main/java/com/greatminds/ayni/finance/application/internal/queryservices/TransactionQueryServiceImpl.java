package com.greatminds.ayni.finance.application.internal.queryservices;

import com.greatminds.ayni.finance.domain.model.aggregates.Transaction;
import com.greatminds.ayni.finance.domain.model.queries.GetAllTransactionsQuery;
import com.greatminds.ayni.finance.domain.model.queries.GetTransactionByIdQuery;
import com.greatminds.ayni.finance.domain.services.TransactionQueryService;
import com.greatminds.ayni.finance.infrastructure.persistence.jpa.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TransactionQueryServiceImpl implements TransactionQueryService {

    private final TransactionRepository transactionRepository;
    public TransactionQueryServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<Transaction> handle(GetTransactionByIdQuery query) {
        return transactionRepository.findById(query.transactionId());
    }

    @Override
    public List<Transaction> handle(GetAllTransactionsQuery query) {
        return transactionRepository.findAll();
    }



}
