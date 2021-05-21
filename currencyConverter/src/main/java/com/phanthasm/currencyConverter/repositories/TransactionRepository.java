package com.phanthasm.currencyConverter.repositories;

import com.phanthasm.currencyConverter.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
