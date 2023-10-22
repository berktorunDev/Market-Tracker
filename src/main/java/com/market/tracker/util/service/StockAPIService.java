package com.market.tracker.util.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.market.tracker.model.Stock;

@Service
public class StockAPIService {

    private final String stockApiUrl;
    private final RestTemplate restTemplate;
    private final String alphavantageApiKey;

    public StockAPIService(
            @Value("${stock.api.url}") String stockApiUrl,
            RestTemplate restTemplate,
            @Value("${alphavantage.api.key}") String alphavantageApiKey) {
        this.stockApiUrl = stockApiUrl;
        this.restTemplate = restTemplate;
        this.alphavantageApiKey = alphavantageApiKey;
    }

    /**
     * Fetches a list of stocks from the Alpha Vantage API.
     *
     * @return A list of Stock objects containing stock information.
     * @throws IOException if there is an issue with the API request.
     */
    public List<Stock> fetchStocks() throws IOException {
        // Build the formatted URL with the API key
        String formattedUrl = UriComponentsBuilder.fromHttpUrl(stockApiUrl)
                .queryParam("apikey", alphavantageApiKey)
                .build().toUriString();

        // Make an HTTP GET request to the formatted URL and retrieve CSV data
        String csvData = restTemplate.getForObject(formattedUrl, String.class);

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
}
