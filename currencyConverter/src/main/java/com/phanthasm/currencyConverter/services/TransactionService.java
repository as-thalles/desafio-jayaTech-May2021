package com.phanthasm.currencyConverter.services;

import com.phanthasm.currencyConverter.dto.TransactionDTO;
import com.phanthasm.currencyConverter.dto.TransactionSuccessDTO;
import com.phanthasm.currencyConverter.entities.Currency;
import com.phanthasm.currencyConverter.entities.Transaction;
import com.phanthasm.currencyConverter.repositories.CurrencyRepository;
import com.phanthasm.currencyConverter.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repositoryTransaction;
    @Autowired
    private CurrencyRepository repositoryCurrency;

    public List<TransactionDTO> findAll() {
        return repositoryTransaction.findAll().stream().map(x -> new TransactionDTO(x)).collect(Collectors.toList());
    }

    public TransactionSuccessDTO save(Transaction transaction) {
        Map<String, Currency> map = repositoryCurrency.findAllById(
                                        Arrays.asList(transaction.getCurrencyOrigin(), transaction.getCurrencyTarget())
                                    ).stream().collect(Collectors.toMap(Currency::getName, Function.identity()));

        var currencyO = map.get(transaction.getCurrencyOrigin());
        var currencyT = map.get(transaction.getCurrencyTarget());
        Double exchangeRate = currencyT.getValue() / currencyO.getValue();
        Double targetValue = exchangeRate * transaction.getValue();

        transaction.setExchangeRate(exchangeRate);
        transaction.setDate(transaction.getDateTime() == null ? LocalDateTime.now(ZoneOffset.UTC) : transaction.getDateTime());

        TransactionSuccessDTO tsDTO = new TransactionSuccessDTO(repositoryTransaction.save(transaction));
        tsDTO.setValueTarget(targetValue);

        return tsDTO;
    }
}
