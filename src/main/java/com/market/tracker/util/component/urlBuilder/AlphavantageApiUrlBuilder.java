package com.market.tracker.util.component.urlBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.market.tracker.enums.AlphavantageApiFunctionType;
import com.market.tracker.request.NewsAndSentimentRequest;

/**
 * The AlphavantageApiUrlBuilder class is responsible for building the URL for
 * Alpha Vantage API requests.
 */
@Component
public class AlphavantageApiUrlBuilder {
    @Value("${base.alphavantage.api.url}")
    private String baseUrl;

    @Value("${alphavantage.api.key}")
    private String apiKey;

    /**
     * Build the URL for an Alpha Vantage API request based on the function type,
     * symbol, and request parameters.
     *
     * @param functionType            The type of API function to be used (e.g.,
     *                                GLOBAL_QUOTE, NEWS_SENTIMENT).
     * @param symbol                  The symbol for which the data is requested
     *                                (used in GLOBAL_QUOTE function).
     * @param newsAndSentimentRequest The request parameters for the NEWS_SENTIMENT
     *                                function.
     * @return The fully constructed API URL.
     */
    public String buildUrl(AlphavantageApiFunctionType functionType, String symbol,
            NewsAndSentimentRequest newsAndSentimentRequest) {
        String functionValue = "";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey);

        switch (functionType) {
            case LISTING_STATUS:
                functionValue = "LISTING_STATUS";
                break;
            case GLOBAL_QUOTE:
                functionValue = "GLOBAL_QUOTE";
                builder.queryParam("symbol", symbol);
                break;
            case NEWS_SENTIMENT:
                functionValue = "NEWS_SENTIMENT";
                builder.queryParam("tickers", newsAndSentimentRequest.getTickers())
                        .queryParam("limit", newsAndSentimentRequest.getLimit());
                break;
            case TOP_GAINERS_LOSERS:
                functionValue = "TOP_GAINERS_LOSERS";
        }

        builder.queryParam("function", functionValue);

        return builder.toUriString();
    }
}
