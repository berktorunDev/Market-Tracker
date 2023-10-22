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

import com.market.tracker.model.Crypto;
import com.market.tracker.service.CryptoService;
import com.market.tracker.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/cryptos")
public class CryptoController {

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllCryptos(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Crypto> cryptos = cryptoService.getAllCryptos(pageRequest);
        return ResponseHandler.successResponse(HttpStatus.OK, "Cryptos retrieved successfully", cryptos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCryptoById(@PathVariable Long id) {
        Optional<Crypto> crypto = cryptoService.getCryptoById(id);
        if (crypto.isPresent()) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Crypto retrieved successfully", crypto.get());
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Crypto not found");
        }
    }
}
