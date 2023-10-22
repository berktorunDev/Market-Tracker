package com.market.tracker.service;

import com.market.tracker.model.Stock;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.market.tracker.repository.StockRepository;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Page<Stock> getAllStocks(Pageable pageable) {
        return stockRepository.findAll(pageable);
    }

    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    public void saveStocks(List<Stock> stocks) {
        stockRepository.saveAll(stocks);
    }

    public boolean hasStockData() {
        List<Stock> stocks = stockRepository.findAll();
        return !stocks.isEmpty();
    }
}
