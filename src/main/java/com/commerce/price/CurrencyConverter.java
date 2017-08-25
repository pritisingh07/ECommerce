package com.commerce.price;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyConverter {

    GenericCurrencyRateRepository currencyRateRepository;

    public Map<String, Double> calculatePriceInOtherCurrencies(final Map<String, Object> priceMap) {
        String baseCurrency = priceMap.get("base").toString();
        Double amount = (Double) priceMap.get("amount");
        List<String> currencies = (ArrayList<String>) priceMap.get("supportedCurrencies");

        Map<String, Double> amountInOtherCurrencies = new HashMap<>();
        Map<String, Object> ratesInOtherCurrencies = currencyRateRepository.getRatesFor(baseCurrency, currencies);
        Map<String, Double> ratesMap = (Map<String, Double>) ratesInOtherCurrencies.get("rates");

        currencies.stream().forEach(currency ->
                amountInOtherCurrencies.put(currency, amount * ratesMap.get(currency)));

        return amountInOtherCurrencies;
    }

    @Autowired
    public void setCurrencyRateRepository(final GenericCurrencyRateRepository currencyRateRepository) {
        this.currencyRateRepository = currencyRateRepository;
    }


}
