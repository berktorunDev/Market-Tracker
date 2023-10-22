package com.market.tracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.market.tracker.model.Crypto;

@Service
public class CryptoAPIService {

    private final String cryptoApiUrl;
    private final RestTemplate restTemplate;

    // Constructor to initialize CryptoAPIService with the crypto API URL and a
    // RestTemplate
    public CryptoAPIService(@Value("${crypto.api.url}") String cryptoApiUrl, RestTemplate restTemplate) {
        this.cryptoApiUrl = cryptoApiUrl;
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches a list of cryptocurrencies from the provided API.
     *
     * @return A list of Crypto objects containing cryptocurrency information.
     */
    public List<Crypto> fetchCryptos() {
        // Make an HTTP GET request to the crypto API URL
        ResponseEntity<List<Object>> response = restTemplate.exchange(
                cryptoApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Object>>() {
                });

        // Initialize a list to store cryptocurrency data
        List<Crypto> cryptos = new ArrayList<>();

        // Loop through the response data and extract cryptocurrency details
        for (Object cryptoData : response.getBody()) {
            // Check if the data is a map
            if (cryptoData instanceof Map) {
                // Extract the symbol and name of the cryptocurrency
                Object symbolObj = ((Map<String, Object>) cryptoData).get("symbol");
                Object nameObj = ((Map<String, Object>) cryptoData).get("name");

                // Ensure symbol and name are of the expected type (String)
                if (symbolObj != null && symbolObj instanceof String &&
                        nameObj instanceof String) {
                    // Create a Crypto object and set symbol and name
                    Crypto crypto = new Crypto();
                    crypto.setSymbol((String) symbolObj);
                    crypto.setName((String) nameObj);
                    // Add the cryptocurrency to the list
                    cryptos.add(crypto);
                }
            }
        }

        // Return the list of cryptocurrencies
        return cryptos;
    }
}