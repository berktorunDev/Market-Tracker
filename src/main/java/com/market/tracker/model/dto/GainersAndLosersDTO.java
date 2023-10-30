package com.market.tracker.model.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.market.tracker.util.serializerDeserializer.GainersAndLosersDTODeserializer;

@JsonDeserialize(using = GainersAndLosersDTODeserializer.class)
public class GainersAndLosersDTO {
    private String metadata;

    private String lastUpdated;

    private List<AssetInfoDTO> topGainers;

    private List<AssetInfoDTO> topLosers;

    private List<AssetInfoDTO> mostActivelyTraded;

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<AssetInfoDTO> getTopGainers() {
        return topGainers;
    }

    public void setTopGainers(List<AssetInfoDTO> topGainers) {
        this.topGainers = topGainers;
    }

    public List<AssetInfoDTO> getTopLosers() {
        return topLosers;
    }

    public void setTopLosers(List<AssetInfoDTO> topLosers) {
        this.topLosers = topLosers;
    }

    public List<AssetInfoDTO> getMostActivelyTraded() {
        return mostActivelyTraded;
    }

    public void setMostActivelyTraded(List<AssetInfoDTO> mostActivelyTraded) {
        this.mostActivelyTraded = mostActivelyTraded;
    }
}
