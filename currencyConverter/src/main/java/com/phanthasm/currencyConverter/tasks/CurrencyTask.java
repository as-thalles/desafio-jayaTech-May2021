package com.phanthasm.currencyConverter.tasks;

import com.phanthasm.currencyConverter.dto.CurrencyRatesRequestDTO;
import com.phanthasm.currencyConverter.entities.Currency;
import com.phanthasm.currencyConverter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyTask {
    @Autowired
    CurrencyRepository repositoryCurrency;

    @Scheduled(fixedRate = 1000 * 60 * 60)  // 1 hour
    private void getRatesTask() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            CurrencyRatesRequestDTO iterableObj = restTemplate.getForObject("http://api.exchangeratesapi.io/latest?base=EUR&access_key=9727529ac55f7d5f929e03a5d4de58b8", CurrencyRatesRequestDTO.class);
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
