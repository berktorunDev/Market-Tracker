package com.market.tracker.util.api;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.tracker.model.Stock;
import com.market.tracker.model.dto.GainersAndLosersDTO;
import com.market.tracker.model.dto.GlobalQuoteDTO;
import com.market.tracker.model.dto.NewsAndSentimentDTO;
import com.market.tracker.model.enums.AlphavantageApiFunctionType;
import com.market.tracker.model.request.NewsAndSentimentRequest;
import com.market.tracker.util.component.urlBuilder.AlphavantageApiUrlBuilder;

@Service
public class AlphavantageAPI {

    private final AlphavantageApiUrlBuilder apiUrlBuilder;
    private final RestTemplate restTemplate;

    public AlphavantageAPI(AlphavantageApiUrlBuilder apiUrlBuilder, RestTemplate restTemplate) {
        this.apiUrlBuilder = apiUrlBuilder;
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches a list of stocks from the Alpha Vantage API.
     *
     * @return A list of Stock objects containing stock information.
     * @throws IOException if there is an issue with the API request.
     */
    public List<Stock> fetchStocks() throws IOException {
        // Build the formatted URL with the API key
        String generatedEndpoint = apiUrlBuilder.buildUrl(AlphavantageApiFunctionType.LISTING_STATUS, null, null);

        // Make an HTTP GET request to the formatted URL and retrieve CSV data
        String csvData = restTemplate.getForObject(generatedEndpoint, String.class);

        // Initialize a list to store stock data
        List<Stock> stocks = new ArrayList<>();

        // Parse the CSV data
        CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new StringReader(csvData));

        // Loop through the CSV records and extract stock details
        for (CSVRecord record : csvParser) {
            String symbol = record.get("symbol");
            String name = record.get("name");
            if (symbol != null && !symbol.isEmpty() && name != null && !name.isEmpty()) {
                // Create a Stock object and set symbol and name
                Stock stock = new Stock();
                stock.setSymbol(symbol);
                stock.setName(name);
                // Add the stock to the list
                stocks.add(stock);
            }
        }

        // Return the list of stocks
        return stocks;
    }

    public GlobalQuoteDTO getGlobalQuote(String symbol) throws JsonMappingException, JsonProcessingException {
        String generatedEndpoint = apiUrlBuilder.buildUrl(AlphavantageApiFunctionType.GLOBAL_QUOTE, symbol, null);
        ResponseEntity<String> response = restTemplate.getForEntity(generatedEndpoint, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String jsonBody = response.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            GlobalQuoteDTO globalQuoteDTO = objectMapper.readValue(jsonBody, GlobalQuoteDTO.class);
            return globalQuoteDTO;

        }

        return null;
    }

    public GainersAndLosersDTO getTopGainersAndLosers() throws JsonMappingException, JsonProcessingException {
        String generatedEndpoint = apiUrlBuilder.buildUrl(AlphavantageApiFunctionType.TOP_GAINERS_LOSERS, null, null);
        ResponseEntity<String> response = restTemplate.getForEntity(generatedEndpoint, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String jsonBody = response.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            GainersAndLosersDTO gainersAndLosersDTO = objectMapper.readValue(jsonBody, GainersAndLosersDTO.class);
            return gainersAndLosersDTO;

        }
        return null;
    }

    public NewsAndSentimentDTO getNewsAndSentiment(NewsAndSentimentRequest newsAndSentimentRequest)
            throws JsonMappingException, JsonProcessingException {
        String generatedEndpoint = apiUrlBuilder.buildUrl(AlphavantageApiFunctionType.NEWS_SENTIMENT, null,
                newsAndSentimentRequest);
        ResponseEntity<String> response = restTemplate.getForEntity(generatedEndpoint, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String jsonBody = response.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            NewsAndSentimentDTO newsAndSentimentDTO = objectMapper.readValue(jsonBody, NewsAndSentimentDTO.class);
            return newsAndSentimentDTO;

        }
        return null;
    }

}
