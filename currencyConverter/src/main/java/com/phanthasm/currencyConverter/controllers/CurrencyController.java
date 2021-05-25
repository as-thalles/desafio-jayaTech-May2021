package com.phanthasm.currencyConverter.controllers;

import com.phanthasm.currencyConverter.dto.CurrencyDTO;
import com.phanthasm.currencyConverter.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/currencies")
public class CurrencyController {
    @Autowired
    private CurrencyService serviceCurrency;

    @GetMapping
    public ResponseEntity<List<CurrencyDTO>> findAll() {
        return ResponseEntity.ok(serviceCurrency.findAll());
    }
}
