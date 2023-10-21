package com.market.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.tracker.model.Crypto;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {

}
