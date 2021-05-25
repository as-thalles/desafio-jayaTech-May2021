package com.phanthasm.currencyConverter.dto;

import com.phanthasm.currencyConverter.entities.Currency;

public class CurrencyDTO {
    private String name;
    private Double value;

    public CurrencyDTO() {}

    public CurrencyDTO(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public CurrencyDTO(Currency currency) {
        this.name = currency.getName();
        this.value = currency.getValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
