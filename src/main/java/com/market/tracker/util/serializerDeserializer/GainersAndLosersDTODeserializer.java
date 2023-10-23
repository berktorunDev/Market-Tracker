package com.market.tracker.util.serializerDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.market.tracker.dto.AssetInfoDTO;
import com.market.tracker.dto.GainersAndLosersDTO;

/**
 * Custom JSON deserializer for converting JSON data into a GainersAndLosersDTO
 * object.
 */
public class GainersAndLosersDTODeserializer extends StdDeserializer<GainersAndLosersDTO> {

    public GainersAndLosersDTODeserializer() {
        this(null);
    }

    public GainersAndLosersDTODeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GainersAndLosersDTO deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode rootNode = jp.getCodec().readTree(jp);

        // Create a GainersAndLosersDTO object from JSON
        GainersAndLosersDTO dto = new GainersAndLosersDTO();

        // Get the metadata field
        dto.setMetadata(rootNode.get("metadata").asText());

        // Get the last_updated field
        dto.setLastUpdated(rootNode.get("last_updated").asText());

        // Get and convert the top gainers field
        List<AssetInfoDTO> topGainers = new ArrayList<>();
        JsonNode topGainersNode = rootNode.get("top_gainers");
        for (JsonNode stockNode : topGainersNode) {
            AssetInfoDTO stockInfo = new AssetInfoDTO();
            stockInfo.setTicker(stockNode.get("ticker").asText());
            stockInfo.setPrice(stockNode.get("price").asText());
            stockInfo.setChangeAmount(stockNode.get("change_amount").asText());
            stockInfo.setChangePercentage(stockNode.get("change_percentage").asText());
            stockInfo.setVolume(stockNode.get("volume").asText());
            topGainers.add(stockInfo);
        }
        dto.setTopGainers(topGainers);

        // Get and convert the top losers field
        List<AssetInfoDTO> topLosers = new ArrayList<>();
        JsonNode topLosersNode = rootNode.get("top_losers");
        for (JsonNode stockNode : topLosersNode) {
            AssetInfoDTO stockInfo = new AssetInfoDTO();
            stockInfo.setTicker(stockNode.get("ticker").asText());
            stockInfo.setPrice(stockNode.get("price").asText());
            stockInfo.setChangeAmount(stockNode.get("change_amount").asText());
            stockInfo.setChangePercentage(stockNode.get("change_percentage").asText());
            stockInfo.setVolume(stockNode.get("volume").asText());
            topLosers.add(stockInfo);
        }
        dto.setTopLosers(topLosers);

        // Get and convert the most actively traded field
        List<AssetInfoDTO> mostActivelyTraded = new ArrayList<>();
        JsonNode mostActivelyTradedNode = rootNode.get("most_actively_traded");
        for (JsonNode stockNode : mostActivelyTradedNode) {
            AssetInfoDTO stockInfo = new AssetInfoDTO();
            stockInfo.setTicker(stockNode.get("ticker").asText());
            stockInfo.setPrice(stockNode.get("price").asText());
            stockInfo.setChangeAmount(stockNode.get("change_amount").asText());
            stockInfo.setChangePercentage(stockNode.get("change_percentage").asText());
            stockInfo.setVolume(stockNode.get("volume").asText());
            mostActivelyTraded.add(stockInfo);
        }
        dto.setMostActivelyTraded(mostActivelyTraded);

        return dto;
    }
}