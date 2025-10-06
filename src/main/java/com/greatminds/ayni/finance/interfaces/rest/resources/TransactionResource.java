package com.greatminds.ayni.finance.interfaces.rest.resources;

public record TransactionResource(
        Long id, String costName, String description, String date, String transactionType, Double price, String quantity, Long userId
) {
}
