package com.phanthasm.currencyConverter.dto;

import com.phanthasm.currencyConverter.entities.Transaction;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransactionDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;
    private String currencyOrigin;
    private String currencyTarget;
    private Double value;
    private Double exchangeRate;
    private LocalDateTime date;
    private UserDTO user;

    public TransactionDTO() {}

    public TransactionDTO(Long id, String currencyOrigin, String currencyTarget, Double value, Double exchangeRate, LocalDateTime date, UserDTO user) {
        this.id = id;
        this.currencyOrigin = currencyOrigin;
        this.currencyTarget = currencyTarget;
        this.value = value;
        this.exchangeRate = exchangeRate;
        this.date = date;
        this.user = user;
    }

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.currencyOrigin = transaction.getCurrencyOrigin();
        this.currencyTarget = transaction.getCurrencyTarget();
        this.value = transaction.getValue();
        this.exchangeRate = transaction.getExchangeRate();
        this.date = transaction.getDate();
        this.user = new UserDTO( transaction.getUser() );
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyOrigin() {
        return currencyOrigin;
    }

    public void setCurrencyOrigin(String currencyOrigin) {
        this.currencyOrigin = currencyOrigin;
    }

    public String getCurrencyTarget() {
        return currencyTarget;
    }

    public void setCurrencyTarget(String currencyTarget) {
        this.currencyTarget = currencyTarget;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
