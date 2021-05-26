package com.phanthasm.currencyConverter.controllers;

import com.phanthasm.currencyConverter.dto.TransactionDTO;
import com.phanthasm.currencyConverter.dto.TransactionSuccessDTO;
import com.phanthasm.currencyConverter.entities.Transaction;
import com.phanthasm.currencyConverter.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {
    @Autowired
    private TransactionService serviceTransaction;

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAll() {
        return ResponseEntity.ok( serviceTransaction.findAll() );
    }

    @PostMapping(value="/convert")
    ResponseEntity<TransactionSuccessDTO> convert(@RequestBody Transaction transaction) {
        return ResponseEntity.ok( serviceTransaction.save(transaction) );
    }

    @GetMapping(value="/byIdUser")
    ResponseEntity<List<TransactionDTO>> findByIdUser(@RequestParam Long idUser) {
        return ResponseEntity.ok( serviceTransaction.findByUser(idUser) );
    }
}
