package com.market.tracker.service;

import com.market.tracker.dto.StockDTO;
import com.market.tracker.model.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.market.tracker.repository.StockRepository;
import com.market.tracker.util.mapper.MapperUtil;

/**
 * The StockService class provides methods for managing stock-related data.
 */
@Service
public class StockService {

    private final StockRepository stockRepository;
    private final MapperUtil mapperUtil;

    /**
     * Constructor to inject the required dependencies.
     *
     * @param stockRepository The repository for stock data.
     * @param mapperUtil      The utility for mapping between entities and DTOs.
     */
    public StockService(StockRepository stockRepository, MapperUtil mapperUtil) {
        this.stockRepository = stockRepository;
        this.mapperUtil = mapperUtil;
    }

    /**
     * Retrieve a paginated list of all stock assets.
     *
     * @param pageable Pagination information.
     * @return A page of StockDTO objects representing stock assets.
     */
    public Page<StockDTO> getAllStocks(Pageable pageable) {
        Page<Stock> stocksPage = stockRepository.findAll(pageable);
        return stocksPage.map(stock -> mapperUtil.convertToDTO(stock, StockDTO.class));
    }

    /**
     * Retrieve a stock asset by its unique identifier.
     *
     * @param id The unique identifier of the stock asset.
     * @return An Optional containing a StockDTO if the asset exists, or an empty
     *         Optional if not found.
     */
    public Optional<StockDTO> getStockById(UUID id) {
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.map(value -> mapperUtil.convertToDTO(value, StockDTO.class));
    }

    /**
     * Save a list of stock assets to the database.
     *
     * @param stocks A list of Stock objects to be saved.
     */
    public void saveStocks(List<Stock> stocks) {
        stockRepository.saveAll(stocks);
    }

    /**
     * Check if there is any stock data available in the database.
     *
     * @return true if stock data exists, false otherwise.
     */
    public boolean hasStockData() {
        List<Stock> stocks = stockRepository.findAll();
        return !stocks.isEmpty();
    }
}
