package com.market.tracker.util.component.task;

import java.io.IOException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    // Inject the necessary tasks
    private final CryptoTask cryptoTask;
    private final StockTask stockTask;

    // Constructor to inject the required task dependencies
    public ScheduledTasks(CryptoTask cryptoTask, StockTask stockTask) {
        this.cryptoTask = cryptoTask;
        this.stockTask = stockTask;
    }

    // Schedule the updateData method to run at a once every 10 days 
    @Scheduled(cron = "0 0 0 */10 * *")
    public void updateData() throws IOException {
        // Call the updateCryptos method in the CryptoTask to update cryptocurrency data
        cryptoTask.updateCryptos();

        // Call the updateStockData method in the StockTask to update stock data
        stockTask.updateStockData();
    }
}
