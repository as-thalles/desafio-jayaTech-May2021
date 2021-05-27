package com.phanthasm.currencyConverter.services;

import com.phanthasm.currencyConverter.dto.CurrencyDTO;
import com.phanthasm.currencyConverter.entities.Currency;
import com.phanthasm.currencyConverter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository repositoryCurrency;

    public CurrencyService(CurrencyRepository repositoryCurrency) {
        this.repositoryCurrency = repositoryCurrency;
    }

    public List<CurrencyDTO> findAll() {
        return repositoryCurrency.findAll().stream().map(x -> new CurrencyDTO(x)).collect(Collectors.toList());
    }

    public CurrencyDTO save(Currency currency) {
        return new CurrencyDTO(repositoryCurrency.save(currency));
    }

    public List<CurrencyDTO> findAllById(List<String> ids) {
        return repositoryCurrency.findAllById(ids).stream().map(x -> new CurrencyDTO(x)).collect(Collectors.toList());
    }
}
