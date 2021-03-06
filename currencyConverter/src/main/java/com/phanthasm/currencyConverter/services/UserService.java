package com.phanthasm.currencyConverter.services;

import com.phanthasm.currencyConverter.dto.UserDTO;
import com.phanthasm.currencyConverter.entities.User;
import com.phanthasm.currencyConverter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repositoryUser;

    public UserService(UserRepository repository) {
        this.repositoryUser = repository;
    }

    public List<UserDTO> findAll() {
        return repositoryUser.findAll().stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    public UserDTO save(User user) {
        return new UserDTO(repositoryUser.save(user));
    }

    public Optional<UserDTO> findById(Long id) {
        Optional<User> user = repositoryUser.findById(id);
        return user.isPresent() ? Optional.of(new UserDTO(user.get())) : Optional.empty();
    }
}
