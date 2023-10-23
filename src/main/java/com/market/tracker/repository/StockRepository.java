package com.market.tracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.tracker.model.Stock;

public interface StockRepository extends JpaRepository<Stock, UUID> {

}
