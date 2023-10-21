package com.market.tracker.util.component.task;

import org.springframework.stereotype.Component;

import com.market.tracker.dto.StockData;
import com.market.tracker.service.StockAPIService;

@Component
public class StockTask {

    private final StockAPIService stockAPIService;

    public StockTask(StockAPIService stockAPIService) {
        this.stockAPIService = stockAPIService;
    }

    public void updateStockData() {
        StockData stockData = stockAPIService.fetchStockData();
        stockAPIService.saveStocks(stockData.getRows());
    }
}
