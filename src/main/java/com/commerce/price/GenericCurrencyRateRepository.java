package com.commerce.price;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenericCurrencyRateRepository implements CurrencyRateRepository {
	
	private final String FIXER_API_URL = "https://api.fixer.io/latest";
	
	RestTemplate restTemplate = new RestTemplate();

	private final String COMMA = ",";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getAllRates(String base) {
		ResponseEntity<Map> result = restTemplate.getForEntity(getUri(FIXER_API_URL + "?base=" + base), Map.class);
		
		return (Map<String, Object>) result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getRatesFor(String base, List<String> currencyCodeList) {
		
		String urlString = FIXER_API_URL + "?" + "base=" +base+ "&symbols=" + 
							currencyCodeList.stream().map(currencyCode -> currencyCode).collect(Collectors.joining(COMMA));
								
		
		ResponseEntity<Map> result = restTemplate.getForEntity(getUri(urlString), Map.class);
		
		return result.getBody();
	}
	
	private URI getUri(String urlString) {
		URI uri = null;
		
		try {
			uri = new URI(urlString);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return uri;
	}

}
