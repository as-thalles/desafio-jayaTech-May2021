package com.phanthasm.currencyConverter.dto;

import com.phanthasm.currencyConverter.entities.Transaction;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransactionSuccessDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long idTransaction;
    private Long idUser;
    private String currencyOrigin;
    private String currencyTarget;
    private Double valueOrigin;
    private Double valueTarget;
    private Double exchangeRate;
    private LocalDateTime dateTime;

    public TransactionSuccessDTO() {}

    public TransactionSuccessDTO(Long idTransaction, Long idUser, String currencyOrigin, String currencyTarget, Double valueOrigin, Double valueTarget, Double exchangeRate, LocalDateTime dateTime) {
        this.idTransaction = idTransaction;
        this.idUser = idUser;
        this.currencyOrigin = currencyOrigin;
        this.currencyTarget = currencyTarget;
        this.valueOrigin = valueOrigin;
        this.valueTarget = valueTarget;
        this.exchangeRate = exchangeRate;
        this.dateTime = dateTime;
    }

    public TransactionSuccessDTO(Transaction transaction) {
        this.idTransaction = transaction.getId();
        this.idUser = transaction.getUser().getId();
        this.currencyOrigin = transaction.getCurrencyOrigin();
        this.currencyTarget = transaction.getCurrencyTarget();
        this.valueOrigin = transaction.getValue();
        this.valueTarget = Math.round(transaction.getValue() * transaction.getExchangeRate() * 100) / 100.0;
        this.exchangeRate = transaction.getExchangeRate();
        this.dateTime = transaction.getDateTime();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public Double getValueOrigin() {
        return valueOrigin;
    }

    public void setValueOrigin(Double valueOrigin) {
        this.valueOrigin = valueOrigin;
    }

    public Double getValueTarget() {
        return valueTarget;
    }

    public void setValueTarget(Double valueTarget) {
        this.valueTarget = valueTarget;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
