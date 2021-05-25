package com.phanthasm.currencyConverter.services;

import com.phanthasm.currencyConverter.dto.TransactionDTO;
import com.phanthasm.currencyConverter.dto.TransactionSuccessDTO;
import com.phanthasm.currencyConverter.entities.Transaction;
import com.phanthasm.currencyConverter.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repositoryTransaction;

    public List<TransactionDTO> findAll() {
        return repositoryTransaction.findAll().stream().map(x -> new TransactionDTO(x)).collect(Collectors.toList());
    }

    public TransactionSuccessDTO save(Transaction transaction) {
        transaction.setDate(transaction.getDateTime() == null ? LocalDateTime.now(ZoneOffset.UTC) : transaction.getDateTime());
        return new TransactionSuccessDTO(repositoryTransaction.save(transaction));
    }
}
