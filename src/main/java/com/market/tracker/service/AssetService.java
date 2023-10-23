package com.market.tracker.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.market.tracker.dto.GainersAndLosersDTO;
import com.market.tracker.dto.GlobalQuoteDTO;
import com.market.tracker.dto.NewsAndSentimentDTO;
import com.market.tracker.request.NewsAndSentimentRequest;
import com.market.tracker.util.api.AlphavantageAPI;

/**
 * AssetService is a service class responsible for retrieving various
 * asset-related data
 * from the AlphavantageAPI.
 */
@Service
public class AssetService {

    private final AlphavantageAPI alphavantageAPI;

    /**
     * Constructor to inject the AlphavantageAPI into the service.
     *
     * @param alphavantageAPI An instance of AlphavantageAPI for making API
     *                        requests.
     */
    public AssetService(AlphavantageAPI alphavantageAPI) {
        this.alphavantageAPI = alphavantageAPI;
    }

    /**
     * Fetch the global quote of an asset by its symbol.
     *
     * @param symbol The symbol of the asset for which to retrieve the global quote.
     * @return The GlobalQuoteDTO representing the global quote of the asset.
     * @throws JsonMappingException    if there's an issue with JSON mapping.
     * @throws JsonProcessingException if there's an issue with JSON processing.
     */
    public GlobalQuoteDTO getGlobalQuote(String symbol) throws JsonMappingException, JsonProcessingException {
        // Call AlphavantageAPI to retrieve the global quote for the given symbol
        GlobalQuoteDTO globalQuoteDTO = alphavantageAPI.getGlobalQuote(symbol);

        // Check if the global quote is retrieved successfully
        if (globalQuoteDTO != null) {
            return globalQuoteDTO;
        }

        // Return null if the global quote is not found
        return null;
    }

    /**
     * Fetch the list of top gainers and losers.
     *
     * @return The GainersAndLosersDTO representing the list of top gainers and
     *         losers.
     * @throws JsonMappingException    if there's an issue with JSON mapping.
     * @throws JsonProcessingException if there's an issue with JSON processing.
     */
    public GainersAndLosersDTO getTopGainersAndLosers() throws JsonMappingException, JsonProcessingException {
        // Call AlphavantageAPI to retrieve the list of top gainers and losers
        GainersAndLosersDTO gainersAndLosersDTO = alphavantageAPI.getTopGainersAndLosers();

        // Check if the list is retrieved successfully
        if (gainersAndLosersDTO != null) {
            return gainersAndLosersDTO;
        }

        // Return null if the list is not found
        return null;
    }

    /**
     * Fetch news and sentiment data based on the request.
     *
     * @param newsAndSentimentRequest The request object specifying the parameters
     *                                for data retrieval.
     * @return The NewsAndSentimentDTO representing news and sentiment data.
     * @throws JsonMappingException    if there's an issue with JSON mapping.
     * @throws JsonProcessingException if there's an issue with JSON processing.
     */
    public NewsAndSentimentDTO getNewsAndSentiment(NewsAndSentimentRequest newsAndSentimentRequest)
            throws JsonMappingException, JsonProcessingException {
        // Call AlphavantageAPI to retrieve news and sentiment data based on the request
        NewsAndSentimentDTO newsAndSentimentDTO = alphavantageAPI.getNewsAndSentiment(newsAndSentimentRequest);

        // Check if news and sentiment data is retrieved successfully
        if (newsAndSentimentDTO != null) {
            return newsAndSentimentDTO;
        }

        // Return null if news and sentiment data is not found
        return null;
    }
}
