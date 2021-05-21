package com.phanthasm.currencyConverter.services;

import com.phanthasm.currencyConverter.dto.UserDTO;
import com.phanthasm.currencyConverter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repositoryUser;

    public List<UserDTO> findAll() {
        return repositoryUser.findAll().stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }
}
