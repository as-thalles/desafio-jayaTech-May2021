package com.phanthasm.currencyConverter.repositories;

import com.phanthasm.currencyConverter.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(" SELECT t FROM Transaction t WHERE t.user.id = :idUser ")
    List<Transaction> findByUser(@Param("idUser") Long idUser);
}
