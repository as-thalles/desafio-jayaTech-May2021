package com.phanthasm.currencyConverter.controllers;

import com.phanthasm.currencyConverter.dto.UserDTO;
import com.phanthasm.currencyConverter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserController {
    @Autowired
    private UserService serviceUser;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok( serviceUser.findAll() );
    }
}
