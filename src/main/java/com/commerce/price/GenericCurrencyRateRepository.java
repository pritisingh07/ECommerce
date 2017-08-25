package com.commerce.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GenericCurrencyRateRepository implements CurrencyRateRepository {

    private final String FIXER_API_URL = "https://api.fixer.io/latest";
    private final String COMMA = ",";
    private final RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOG = LoggerFactory.getLogger(GenericCurrencyRateRepository.class);

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Map<String, Object> getAllRates(final String base) {
        ResponseEntity<Map> result = restTemplate.getForEntity(getUri(FIXER_API_URL + "?base=" + base), Map.class);
        return (Map<String, Object>) result;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Map<String, Object> getRatesFor(final String base, final List<String> currencyCodeList) {

        String urlString = FIXER_API_URL + "?" + "base=" + base + "&symbols=" +
                currencyCodeList.stream().map(currencyCode -> currencyCode).collect(Collectors.joining(COMMA));
        ResponseEntity<Map> result = restTemplate.getForEntity(getUri(urlString), Map.class);
        return result.getBody();
    }

    private URI getUri(final String urlString) {
        URI uri = null;
        try {
            uri = new URI(urlString);
        } catch (URISyntaxException e) {
            LOG.error("URISyntaxException {}" +e.toString());
        }
        return uri;
    }

}
