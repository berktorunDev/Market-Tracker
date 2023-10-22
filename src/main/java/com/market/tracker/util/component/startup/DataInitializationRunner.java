package com.market.tracker.util.component.startup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.market.tracker.service.CryptoService;
import com.market.tracker.service.StockService;
import com.market.tracker.util.component.task.CryptoTask;
import com.market.tracker.util.component.task.StockTask;

@Component
public class DataInitializationRunner implements ApplicationRunner {

    // Create a logger instance
    private Log logger = LogFactory.getLog(getClass());

    // Inject the necessary services and tasks
    private final CryptoService cryptoService;
    private final StockService stockService;
    private final CryptoTask cryptoTask;
    private final StockTask stockTask;

    // Constructor to inject the required dependencies
    public DataInitializationRunner(CryptoService cryptoService, StockService stockService,
            CryptoTask cryptoTask, StockTask stockTask) {
        this.cryptoService = cryptoService;
        this.stockService = stockService;
        this.cryptoTask = cryptoTask;
        this.stockTask = stockTask;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Check if stock data is already available in the database
        if (!stockService.hasStockData()) {
            // If not, run the task to update stock data
            stockTask.updateStockData();
            // Log a successful message
            logger.info("Stock codes are fetched from the external API successfully!!!");
        }

        // Check if cryptocurrency data is already available in the database
        if (!cryptoService.hasCryptoData()) {
            // If not, run the task to update cryptocurrency data
            cryptoTask.updateCryptos();
            // Log a successful message
            logger.info("Crypto currency codes are fetched from the external API successfully!!!");
        }
    }
}
