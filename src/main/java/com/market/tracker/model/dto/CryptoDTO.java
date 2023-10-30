package com.market.tracker.model.dto;

import java.util.UUID;

public class CryptoDTO {
    private UUID id;

    private String symbol;

    private String name;

    private final String prefix = "CRYPTO:";

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }
}
