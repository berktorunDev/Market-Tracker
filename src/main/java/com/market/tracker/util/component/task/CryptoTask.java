package com.market.tracker.util.component.task;

import java.util.List;

import org.springframework.stereotype.Component;

import com.market.tracker.model.Crypto;
import com.market.tracker.service.CryptoAPIService;

@Component
public class CryptoTask {

    private final CryptoAPIService cryptoAPIService;

    public CryptoTask(CryptoAPIService cryptoAPIService) {
        this.cryptoAPIService = cryptoAPIService;
    }

    public void updateCryptos() {
        List<Crypto> cryptos = cryptoAPIService.fetchCryptos();
        cryptoAPIService.saveCryptos(cryptos);
    }
}
