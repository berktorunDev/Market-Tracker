package com.market.tracker.util.component.task;

import java.util.List;

import org.springframework.stereotype.Component;

import com.market.tracker.model.Crypto;
import com.market.tracker.service.CryptoService;
import com.market.tracker.util.service.CryptoAPIService;

@Component
public class CryptoTask {

    // Inject the required services
    private final CryptoService cryptoService;
    private final CryptoAPIService cryptoAPIService;

    // Constructor to inject the necessary dependencies
    public CryptoTask(CryptoService cryptoService, CryptoAPIService cryptoAPIService) {
        this.cryptoService = cryptoService;
        this.cryptoAPIService = cryptoAPIService;
    }

    // Method to update cryptocurrency data
    public void updateCryptos() {
        // Fetch a list of cryptocurrency data from an external API using the
        // CryptoAPIService
        List<Crypto> cryptos = cryptoAPIService.fetchCryptos();

        // Save the fetched cryptocurrency data to the database using the CryptoService
        cryptoService.saveCryptos(cryptos);
    }
}
