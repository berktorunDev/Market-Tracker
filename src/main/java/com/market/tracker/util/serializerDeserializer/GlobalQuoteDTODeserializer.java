package com.market.tracker.util.serializerDeserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.market.tracker.model.dto.GlobalQuoteDTO;

/**
 * Custom JSON deserializer for converting JSON data into a GlobalQuoteDTO
 * object.
 */
public class GlobalQuoteDTODeserializer extends StdDeserializer<GlobalQuoteDTO> {

    public GlobalQuoteDTODeserializer() {
        this(null);
    }

    public GlobalQuoteDTODeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GlobalQuoteDTO deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode parentNode = jp.getCodec().readTree(jp);
        JsonNode node = parentNode.get("Global Quote");

        GlobalQuoteDTO globalQuoteDTO = new GlobalQuoteDTO();
        globalQuoteDTO.setSymbol(node.get("01. symbol").asText());
        globalQuoteDTO.setOpen(node.get("02. open").asText());
        globalQuoteDTO.setHigh(node.get("03. high").asText());
        globalQuoteDTO.setLow(node.get("04. low").asText());
        globalQuoteDTO.setPrice(node.get("05. price").asText());
        globalQuoteDTO.setVolume(node.get("06. volume").asText());
        globalQuoteDTO.setLatestTradingDay(node.get("07. latest trading day").asText());
        globalQuoteDTO.setPreviousClose(node.get("08. previous close").asText());
        globalQuoteDTO.setChange(node.get("09. change").asText());
        globalQuoteDTO.setChangePercent(node.get("10. change percent").asText());

        return globalQuoteDTO;
    }
}