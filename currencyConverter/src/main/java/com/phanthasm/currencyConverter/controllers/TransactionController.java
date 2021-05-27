package com.phanthasm.currencyConverter.controllers;

import com.phanthasm.currencyConverter.dto.TransactionDTO;
import com.phanthasm.currencyConverter.dto.TransactionSuccessDTO;
import com.phanthasm.currencyConverter.entities.Transaction;
import com.phanthasm.currencyConverter.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<TransactionSuccessDTO> result = serviceTransaction.convert(transaction);
        if(result.isPresent()) {
            return ResponseEntity.ok( result.get() );
        }
        return new ResponseEntity("Specified parameters are invalid.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value="/byIdUser")
    ResponseEntity<List<TransactionDTO>> findByIdUser(@RequestParam Long idUser) {
        return ResponseEntity.ok( serviceTransaction.findByUser(idUser) );
    }
}
