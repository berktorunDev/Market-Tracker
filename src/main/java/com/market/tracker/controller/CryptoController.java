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

import com.market.tracker.model.dto.CryptoDTO;
import com.market.tracker.service.CryptoService;
import com.market.tracker.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    // Constructor to inject CryptoService into the controller
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    // Endpoint to retrieve a paginated list of all cryptocurrencies
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllCryptos(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size) {
        // Create a PageRequest for pagination
        PageRequest pageRequest = PageRequest.of(page, size);

        // Call the CryptoService to fetch a page of cryptocurrencies
        Page<CryptoDTO> cryptos = cryptoService.getAllCryptos(pageRequest);

        // Check if data is retrieved successfully
        if (!cryptos.isEmpty()) {
            // Return a success response with the fetched data
            return ResponseHandler.successResponse(HttpStatus.OK, "Cryptos retrieved successfully", cryptos);
        } else {
            // Return an error response if no data is found
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "No cryptocurrencies found");
        }
    }

    // Endpoint to retrieve a cryptocurrency by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCryptoById(@PathVariable UUID id) {
        // Call the CryptoService to fetch a cryptocurrency by its ID
        Optional<CryptoDTO> crypto = cryptoService.getCryptoById(id);

        // Check if the cryptocurrency with the given ID is found
        if (crypto.isPresent()) {
            // Return a success response with the fetched cryptocurrency
            return ResponseHandler.successResponse(HttpStatus.OK, "Crypto retrieved successfully", crypto.get());
        } else {
            // Return an error response if the cryptocurrency is not found
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Crypto not found");
        }
    }
}
