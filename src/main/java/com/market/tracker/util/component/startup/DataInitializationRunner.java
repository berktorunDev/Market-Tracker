package com.market.tracker.util.component.startup;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.market.tracker.service.CryptoAPIService;
import com.market.tracker.service.StockAPIService;
import com.market.tracker.util.component.task.CryptoTask;
import com.market.tracker.util.component.task.StockTask;

@Component
public class DataInitializationRunner implements ApplicationRunner {

    private final CryptoAPIService cryptoAPIService;
    private final StockAPIService stockAPIService;
    private final CryptoTask cryptoTask;
    private final StockTask stockTask;

    public DataInitializationRunner(CryptoAPIService cryptoAPIService, StockAPIService stockAPIService, CryptoTask cryptoTask, StockTask stockTask) {
        this.cryptoAPIService = cryptoAPIService;
        this.stockAPIService = stockAPIService;
        this.cryptoTask = cryptoTask;
        this.stockTask = stockTask;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Mevcut verilerin kontrolünü yap
        if (!cryptoAPIService.hasCryptoData()) {
            // Crypto görevini çalıştır
            cryptoTask.updateCryptos();
        }

        if (!stockAPIService.hasStockData()) {
            // Stock görevini çalıştır
            stockTask.updateStockData();
        }
    }
}
