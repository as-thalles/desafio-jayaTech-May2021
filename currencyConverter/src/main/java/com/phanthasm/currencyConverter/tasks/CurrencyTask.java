package com.phanthasm.currencyConverter.tasks;

import com.phanthasm.currencyConverter.dto.CurrencyRatesRequestDTO;
import com.phanthasm.currencyConverter.entities.Currency;
import com.phanthasm.currencyConverter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyTask {
    @Value("${fixer.io.apiKey}")
    private String fixerIoApiKey;

    @Autowired
    CurrencyRepository repositoryCurrency;

    @Scheduled(fixedRate = 6 * 1000 * 60 * 60)  // 6 hours
    private void getRatesTask() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            CurrencyRatesRequestDTO iterableObj = restTemplate.getForObject(fixerIoApiKey, CurrencyRatesRequestDTO.class);
            iterableObj.getRates().forEach((key, value) -> {
                Currency currency = new Currency(key, value);
                this.repositoryCurrency.save(currency);
            });
        } catch (RestClientException exception) {
            System.out.println("Error");
            System.out.println(exception.getMessage());
        }
    }
}
