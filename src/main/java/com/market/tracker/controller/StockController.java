package com.market.tracker.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.market.tracker.dto.StockDTO;
import com.market.tracker.service.StockService;
import com.market.tracker.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    // Constructor to inject StockService into the controller
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // Endpoint to retrieve a paginated list of all stocks
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllStocks(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size) {
        // Create a PageRequest for pagination
        PageRequest pageRequest = PageRequest.of(page, size);

        // Call the StockService to fetch a page of stocks
        Page<StockDTO> stocksPage = stockService.getAllStocks(pageRequest);

        // Check if data is retrieved successfully
        if (!stocksPage.isEmpty()) {
            // Return a success response with the fetched data
            return ResponseHandler.successResponse(HttpStatus.OK, "Stocks retrieved successfully", stocksPage);
        } else {
            // Return an error response if no data is found
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "No stocks found");
        }
    }

    // Endpoint to retrieve a stock by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getStockById(@PathVariable UUID id) {
        // Call the StockService to fetch a stock by its ID
        Optional<StockDTO> stock = stockService.getStockById(id);

        // Check if the stock with the given ID is found
        if (stock.isPresent()) {
            // Return a success response with the fetched stock
            return ResponseHandler.successResponse(HttpStatus.OK, "Stock retrieved successfully", stock.get());
        } else {
            // Return an error response if the stock is not found
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Stock not found");
        }
    }
}
