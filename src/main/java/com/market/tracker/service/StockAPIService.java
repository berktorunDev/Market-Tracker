package com.market.tracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.market.tracker.dto.StockData;
import com.market.tracker.dto.StockItem;
import com.market.tracker.model.Stock;
import com.market.tracker.repository.StockRepository;

@Service
public class StockAPIService {

    private final String stockApiUrl;
    private final RestTemplate restTemplate;
    private final StockRepository stockRepository;

    public StockAPIService(
            @Value("${stock.api.url}") String stockApiUrl,
            RestTemplate restTemplate,
            StockRepository stockRepository) {
        this.stockApiUrl = stockApiUrl;
        this.restTemplate = restTemplate;
        this.stockRepository = stockRepository;
    }

    public StockData fetchStockData() {
        return restTemplate.getForObject(stockApiUrl, StockData.class);
    }

    public void saveStocks(List<StockItem> stockItems) {
        List<Stock> stocks = stockItems.stream()
                .map(stockItem -> {
                    Stock stock = new Stock();
                    stock.setSymbol(stockItem.getSymbol());
                    return stock;
                })
                .collect(Collectors.toList());

        stockRepository.saveAll(stocks);
    }

    public boolean hasStockData() {
        List<Stock> stocks = stockRepository.findAll();
        return !stocks.isEmpty();
    }
}
