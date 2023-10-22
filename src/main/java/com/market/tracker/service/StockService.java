package com.market.tracker.service;

import com.market.tracker.dto.StockDTO;
import com.market.tracker.model.Stock;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.market.tracker.repository.StockRepository;
import com.market.tracker.util.mapper.MapperUtil;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final MapperUtil mapperUtil;

    public StockService(StockRepository stockRepository, MapperUtil mapperUtil) {
        this.stockRepository = stockRepository;
        this.mapperUtil = mapperUtil;
    }

    public Page<StockDTO> getAllStocks(Pageable pageable) {
        Page<Stock> stocksPage = stockRepository.findAll(pageable);
        return stocksPage.map(stock -> mapperUtil.convertToDTO(stock, StockDTO.class));
    }

    public Optional<StockDTO> getStockById(Long id) {
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.map(value -> mapperUtil.convertToDTO(value, StockDTO.class));
    }

    public void saveStocks(List<Stock> stocks) {
        stockRepository.saveAll(stocks);
    }

    public boolean hasStockData() {
        List<Stock> stocks = stockRepository.findAll();
        return !stocks.isEmpty();
    }
}
