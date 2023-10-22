package com.market.tracker.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.market.tracker.model.Stock;
import com.market.tracker.service.StockService;
import com.market.tracker.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllStocks(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Stock> stocksPage = stockService.getAllStocks(pageRequest);
        return ResponseHandler.successResponse(HttpStatus.OK, "Stocks retrieved successfully", stocksPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStockById(@PathVariable Long id) {
        Optional<Stock> stock = stockService.getStockById(id);
        if (stock.isPresent()) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Stock retrieved successfully", stock.get());
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Stock not found");
        }
    }
}
