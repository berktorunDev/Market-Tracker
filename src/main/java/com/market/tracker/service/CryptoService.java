package com.market.tracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.market.tracker.model.Crypto;
import com.market.tracker.model.dto.CryptoDTO;
import com.market.tracker.repository.CryptoRepository;
import com.market.tracker.util.mapper.MapperUtil;

/**
 * The CryptoService class provides methods for managing crypto-related data.
 */
@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;
    private final MapperUtil mapperUtil;

    /**
     * Constructor to inject the required dependencies.
     *
     * @param cryptoRepository The repository for crypto data.
     * @param mapperUtil       The utility for mapping between entities and DTOs.
     */
    public CryptoService(CryptoRepository cryptoRepository, MapperUtil mapperUtil) {
        this.cryptoRepository = cryptoRepository;
        this.mapperUtil = mapperUtil;
    }

    /**
     * Retrieve a paginated list of all crypto assets.
     *
     * @param pageable Pagination information.
     * @return A page of CryptoDTO objects representing crypto assets.
     */
    public Page<CryptoDTO> getAllCryptos(Pageable pageable) {
        Page<Crypto> cryptosPage = cryptoRepository.findAll(pageable);
        return cryptosPage.map(crypto -> mapperUtil.convertToDTO(crypto, CryptoDTO.class));
    }

    /**
     * Retrieve a crypto asset by its unique identifier.
     *
     * @param id The unique identifier of the crypto asset.
     * @return An Optional containing a CryptoDTO if the asset exists, or an empty
     *         Optional if not found.
     */
    public Optional<CryptoDTO> getCryptoById(UUID id) {
        Optional<Crypto> crypto = cryptoRepository.findById(id);
        return crypto.map(value -> mapperUtil.convertToDTO(value, CryptoDTO.class));
    }

    /**
     * Save a list of crypto assets to the database.
     *
     * @param cryptos A list of Crypto objects to be saved.
     */
    public void saveCryptos(List<Crypto> cryptos) {
        cryptoRepository.saveAll(cryptos);
    }

    /**
     * Check if there is any crypto data available in the database.
     *
     * @return true if crypto data exists, false otherwise.
     */
    public boolean hasCryptoData() {
        List<Crypto> cryptos = cryptoRepository.findAll();
        return !cryptos.isEmpty();
    }
}
