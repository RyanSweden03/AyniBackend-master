package com.greatminds.ayni.finance.infrastructure.persistence.jpa.repositories;

import com.greatminds.ayni.finance.domain.model.aggregates.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
