package com.phanthasm.currencyConverter.services;

import com.phanthasm.currencyConverter.dto.TransactionDTO;
import com.phanthasm.currencyConverter.repositories.TransactionRepository;
import com.phanthasm.currencyConverter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repositoryTransaction;

    public List<TransactionDTO> findAll() {
        return repositoryTransaction.findAll().stream().map(x -> new TransactionDTO(x)).collect(Collectors.toList());
    }
}
