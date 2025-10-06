package com.greatminds.ayni.finance.interfaces.rest;

import com.greatminds.ayni.finance.domain.model.commands.DeleteTransactionCommand;
import com.greatminds.ayni.finance.domain.model.queries.GetAllTransactionsQuery;
import com.greatminds.ayni.finance.domain.model.queries.GetTransactionByIdQuery;
import com.greatminds.ayni.finance.domain.services.TransactionCommandService;
import com.greatminds.ayni.finance.domain.services.TransactionQueryService;
import com.greatminds.ayni.finance.interfaces.rest.resources.CreateTransactionResource;
import com.greatminds.ayni.finance.interfaces.rest.resources.TransactionResource;
import com.greatminds.ayni.finance.interfaces.rest.resources.UpdateTransactionResource;
import com.greatminds.ayni.finance.interfaces.rest.transform.CreateTransactionCommandFromResourceAssembler;
import com.greatminds.ayni.finance.interfaces.rest.transform.TransactionResourceFromEntityAssembler;
import com.greatminds.ayni.finance.interfaces.rest.transform.UpdateTransactionCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for transaction management.
 * Here is where the endpoints are defined.
 * This class is responsible for handling the requests and responses.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transactions", description = "Financial Transactions Management Endpoints")
public class TransactionController {
    private final TransactionQueryService transactionQueryService;
    private final TransactionCommandService transactionCommandService;

    public TransactionController(TransactionQueryService transactionQueryService, TransactionCommandService transactionCommandService) {
        this.transactionQueryService = transactionQueryService;
        this.transactionCommandService = transactionCommandService;
    }

    /**
     * Handles the creation of a new transaction.
     * @param resource The transaction creation command.
     * @return The ID of the newly created transaction.
     */
    @PostMapping
    public ResponseEntity<TransactionResource> createTransaction(@RequestBody CreateTransactionResource resource) {
        var createTransactionCommand = CreateTransactionCommandFromResourceAssembler.toCommandFromResource(resource);

        var transactionId = transactionCommandService.handle(createTransactionCommand);
        if (transactionId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getTransactionIdByQuery = new GetTransactionByIdQuery(transactionId);
        var transaction = transactionQueryService.handle(getTransactionIdByQuery);
        if (transaction.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(transaction.get());
        return new ResponseEntity<>(transactionResource, HttpStatus.CREATED);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResource> getTransactionById(@PathVariable Long transactionId) {
        var getTransactionIdByQuery = new GetTransactionByIdQuery(transactionId);
        var transaction = transactionQueryService.handle(getTransactionIdByQuery);
        if (transaction.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(transaction.get());
        return ResponseEntity.ok(transactionResource);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResource>> getAllTransactions() {
        var getAllTransactionsQuery = new GetAllTransactionsQuery();
        var transactions = transactionQueryService.handle(getAllTransactionsQuery);
        var transactionResources = transactions.stream().map(TransactionResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(transactionResources);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResource> updateTransaction(@PathVariable Long transactionId, @RequestBody UpdateTransactionResource updateTransactionResource) {
        var updateTransactionCommand = UpdateTransactionCommandFromResourceAssembler.toCommandFromResource(transactionId, updateTransactionResource);
        var updatedTransaction = transactionCommandService.handle(updateTransactionCommand);
        if (updatedTransaction.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(updatedTransaction.get());
        return ResponseEntity.ok(transactionResource);
    }


    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        var deleteTransactionCommand = new DeleteTransactionCommand(transactionId);
        transactionCommandService.handle(deleteTransactionCommand);
        return ResponseEntity.ok("Transaction with given id successfully deleted");
    }
}
