package com.market.tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.market.tracker.dto.CryptoDTO;
import com.market.tracker.model.Crypto;
import com.market.tracker.repository.CryptoRepository;
import com.market.tracker.util.mapper.MapperUtil;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;
    private final MapperUtil mapperUtil;

    public CryptoService(CryptoRepository cryptoRepository, MapperUtil mapperUtil) {
        this.cryptoRepository = cryptoRepository;
        this.mapperUtil = mapperUtil;
    }

    public Page<CryptoDTO> getAllCryptos(Pageable pageable) {
        Page<Crypto> cryptosPage = cryptoRepository.findAll(pageable);
        return cryptosPage.map(crypto -> mapperUtil.convertToDTO(crypto, CryptoDTO.class));
    }

    public Optional<CryptoDTO> getCryptoById(Long id) {
        Optional<Crypto> crypto = cryptoRepository.findById(id);
        return crypto.map(value -> mapperUtil.convertToDTO(value, CryptoDTO.class));
    }

    public void saveCryptos(List<Crypto> cryptos) {
        cryptoRepository.saveAll(cryptos);
    }

    public boolean hasCryptoData() {
        List<Crypto> cryptos = cryptoRepository.findAll();
        return !cryptos.isEmpty();
    }
}
