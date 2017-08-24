package com.commerce.price;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository {
	
	public Map<String, Object> getAllRates(String base);

	public Map<String, Object> getRatesFor(String base, List<String> currencyCodeList);
}
