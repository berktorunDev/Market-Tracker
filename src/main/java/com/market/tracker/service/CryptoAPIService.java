package com.market.tracker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.market.tracker.model.Crypto;
import com.market.tracker.repository.CryptoRepository;

@Service
public class CryptoAPIService {

    private final String cryptoApiUrl;
    private final RestTemplate restTemplate;
    private final CryptoRepository cryptoRepository;

    public CryptoAPIService(@Value("${crypto.api.url}") String cryptoApiUrl, RestTemplate restTemplate,
            CryptoRepository cryptoRepository) {
        this.cryptoApiUrl = cryptoApiUrl;
        this.restTemplate = restTemplate;
        this.cryptoRepository = cryptoRepository;
    }

    public List<Crypto> fetchCryptos() {
        ResponseEntity<List<Object>> response = restTemplate.exchange(cryptoApiUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Object>>() {
                });
        List<Crypto> cryptos = new ArrayList<>();

        for (Object cryptoData : response.getBody()) {
            if (cryptoData instanceof Map) {
                // "symbol" alanını al
                Object symbolObj = ((Map<String, Object>) cryptoData).get("symbol");

                if (symbolObj != null && symbolObj instanceof String) {
                    Crypto crypto = new Crypto();
                    crypto.setSymbol((String) symbolObj);
                    cryptos.add(crypto);
                }
            }
        }

        return cryptos;
    }

    public void saveCryptos(List<Crypto> cryptos) {
        cryptoRepository.saveAll(cryptos);
    }

    public boolean hasCryptoData() {
        List<Crypto> cryptos = cryptoRepository.findAll();
        return !cryptos.isEmpty();
    }
}
