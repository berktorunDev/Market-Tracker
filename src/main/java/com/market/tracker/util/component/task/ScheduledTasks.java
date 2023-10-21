package com.market.tracker.util.component.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final CryptoTask cryptoTask;
    private final StockTask stockTask;

    public ScheduledTasks(CryptoTask cryptoTask, StockTask stockTask) {
        this.cryptoTask = cryptoTask;
        this.stockTask = stockTask;
    }

    @Scheduled(cron = "0 0 0 */10 * *") // Her 10 günde bir saat başı
    public void updateData() {
        cryptoTask.updateCryptos();
        stockTask.updateStockData();
    }
}
