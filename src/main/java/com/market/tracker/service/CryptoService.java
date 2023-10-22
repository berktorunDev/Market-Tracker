package com.market.tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.market.tracker.model.Crypto;
import com.market.tracker.repository.CryptoRepository;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    public CryptoService(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public Page<Crypto> getAllCryptos(Pageable pageable) {
        return cryptoRepository.findAll(pageable);
    }

    public Optional<Crypto> getCryptoById(Long id) {
        return cryptoRepository.findById(id);
    }

    public void saveCryptos(List<Crypto> cryptos) {
        cryptoRepository.saveAll(cryptos);
    }

    public boolean hasCryptoData() {
        List<Crypto> cryptos = cryptoRepository.findAll();
        return !cryptos.isEmpty();
    }
}
