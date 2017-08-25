package com.commerce.price;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CurrencyRateRepository {

    public Map<String, Object> getAllRates(final String base);

    public Map<String, Object> getRatesFor(final String base, final List<String> currencyCodeList);
}
