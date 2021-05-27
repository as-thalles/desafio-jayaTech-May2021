package com.phanthasm.currencyConverter.services;

import com.phanthasm.currencyConverter.dto.CurrencyDTO;
import com.phanthasm.currencyConverter.dto.TransactionDTO;
import com.phanthasm.currencyConverter.dto.TransactionSuccessDTO;
import com.phanthasm.currencyConverter.dto.UserDTO;
import com.phanthasm.currencyConverter.entities.Transaction;
import com.phanthasm.currencyConverter.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repositoryTransaction;
    @Autowired
    private CurrencyService serviceCurrency;
    @Autowired
    private UserService serviceUser;

    public TransactionService(TransactionRepository repository) {
        this.repositoryTransaction = repository;
    }

    public List<TransactionDTO> findAll() {
        return repositoryTransaction.findAll().stream().map(x -> new TransactionDTO(x)).collect(Collectors.toList());
    }

    public TransactionSuccessDTO save(Transaction transaction) {
        transaction.setDate(LocalDateTime.now(ZoneOffset.UTC));
        return new TransactionSuccessDTO(repositoryTransaction.save(transaction));
    }

    public Optional<TransactionSuccessDTO> convert(Transaction transaction) {
        // Validate transaction
        if(!transaction.isValid()) {
            return Optional.empty();
        }

        List<String> currenciesList = Arrays.asList(transaction.getCurrencyOrigin().toUpperCase(), transaction.getCurrencyTarget().toUpperCase());
        Map<String, CurrencyDTO> map = serviceCurrency.findAllById(currenciesList).stream().collect(Collectors.toMap(CurrencyDTO::getName, Function.identity()));
        Optional<CurrencyDTO> currencyO = Optional.ofNullable(map.get(transaction.getCurrencyOrigin().toUpperCase()));
        Optional<CurrencyDTO> currencyT = Optional.ofNullable(map.get(transaction.getCurrencyTarget().toUpperCase()));

        if(currencyO.isEmpty() || currencyT.isEmpty()) {
            return Optional.empty();
        }

        Optional<UserDTO> user = serviceUser.findById(transaction.getUser().getId());
        if(user.isEmpty()) {
            return Optional.empty();
        }

        // Transaction
        Double exchangeRate = Transaction.calculateExchangeRate(currencyO.get().getValue(), currencyT.get().getValue());
        Double targetValue = exchangeRate * transaction.getValue();

        transaction.setExchangeRate(exchangeRate);

        TransactionSuccessDTO tsDTO = this.save(transaction);
        tsDTO.setValueTarget(targetValue);

        return Optional.of(tsDTO);
    }

    public List<TransactionDTO> findByUser(Long idUser) {
        return repositoryTransaction.findByUser(idUser).stream().map(x -> new TransactionDTO(x)).collect(Collectors.toList());
    }
}
