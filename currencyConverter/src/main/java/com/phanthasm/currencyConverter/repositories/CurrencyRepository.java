package com.phanthasm.currencyConverter.repositories;

import com.phanthasm.currencyConverter.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
