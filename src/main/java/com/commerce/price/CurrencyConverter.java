package com.commerce.price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class CurrencyConverter {

	GenericCurrencyRateRepository currencyRateRepository;
	
	@SuppressWarnings("unchecked")
	public Map<String, Double> calculatePriceInOtherCurrencies(Map<String, Object> priceMap) {
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
	public void setCurrencyRateRepository(GenericCurrencyRateRepository currencyRateRepository) {
		this.currencyRateRepository = currencyRateRepository;
	}
	
	
	
}
