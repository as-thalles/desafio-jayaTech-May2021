package com.phanthasm.currencyConverter.repositories;

import com.phanthasm.currencyConverter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
