package com.market.tracker.util.component.task;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.market.tracker.model.Stock;
import com.market.tracker.service.StockAPIService;
import com.market.tracker.service.StockService;

@Component
public class StockTask {

    // Inject the necessary services
    private final StockAPIService stockAPIService;
    private final StockService stockService;

    // Constructor to inject the required dependencies
    public StockTask(StockAPIService stockAPIService, StockService stockService) {
        this.stockAPIService = stockAPIService;
        this.stockService = stockService;
    }

    // Method to update stock data
    public void updateStockData() throws IOException {
        // Fetch a list of stock data from an external API using the StockAPIService
        List<Stock> stocks = stockAPIService.fetchStocks();

        // Save the fetched stock data to the database using the StockService
        stockService.saveStocks(stocks);
    }
}
