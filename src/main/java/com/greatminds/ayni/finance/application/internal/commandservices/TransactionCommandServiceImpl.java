package com.greatminds.ayni.finance.application.internal.commandservices;

import com.greatminds.ayni.finance.domain.model.aggregates.Transaction;
import com.greatminds.ayni.finance.domain.model.commands.CreateTransactionCommand;
import com.greatminds.ayni.finance.domain.model.commands.DeleteTransactionCommand;
import com.greatminds.ayni.finance.domain.model.commands.UpdateTransactionCommand;
import com.greatminds.ayni.finance.domain.services.TransactionCommandService;
import com.greatminds.ayni.finance.infrastructure.persistence.jpa.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionCommandServiceImpl implements TransactionCommandService {

    private final TransactionRepository transactionRepository;
    public TransactionCommandServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Long handle(CreateTransactionCommand command) {
        var transaction = new Transaction(command.costName(), command.description(), command.date(), command.transactionType(),
                command.price(), command.quantity(), command.userId());
        transactionRepository.save(transaction);
        return transaction.getId();
    }

    @Override
    public void handle(DeleteTransactionCommand command) {
        if (!transactionRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Transaction with ID " + command.id() + " not found");
        }
        transactionRepository.deleteById(command.id());
    }

    @Override
    public Optional<Transaction> handle(UpdateTransactionCommand command) {
        if (!transactionRepository.existsById(command.id())) throw new IllegalArgumentException("Transaction does not exist");
        var transactionToUpdate = transactionRepository.findById(command.id()).get();
        var updatedTransaction = transactionRepository.save(transactionToUpdate.update(command.costName(),
                command.description(), command.date(), command.transactionType(), command.price(),
                command.quantity()));
        return Optional.of(updatedTransaction);
    }
}