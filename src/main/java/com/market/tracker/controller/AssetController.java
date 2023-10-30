package com.market.tracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.market.tracker.model.dto.GainersAndLosersDTO;
import com.market.tracker.model.dto.GlobalQuoteDTO;
import com.market.tracker.model.dto.NewsAndSentimentDTO;
import com.market.tracker.model.request.NewsAndSentimentRequest;
import com.market.tracker.service.AssetService;
import com.market.tracker.util.responseHandler.ResponseHandler;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/asset")
public class AssetController {
    private final AssetService assetService;

    // Constructor to inject AssetService into the controller
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    // Endpoint to get global quote data for a given symbol
    @GetMapping("/globalQuote/{symbol}")
    public ResponseEntity<Object> getGlobalQuote(@PathVariable String symbol)
            throws JsonMappingException, JsonProcessingException {
        // Call the AssetService to fetch the global quote data for the specified symbol
        GlobalQuoteDTO globalQuoteDTO = assetService.getGlobalQuote(symbol);

        // Check if data is retrieved successfully
        if (globalQuoteDTO != null) {
            // Return a success response with the fetched data
            return ResponseHandler.successResponse(HttpStatus.OK, "Global quote of asset fetched successfully!!!",
                    globalQuoteDTO);
        }

        // Return an error response if data retrieval failed
        return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "Fetch global quote failed!!!");
    }

    // Endpoint to get top gainers and losers
    @GetMapping("/topGainersAndLosers")
    public ResponseEntity<Object> getTopGainersAndLosers() throws JsonMappingException, JsonProcessingException {
        // Call the AssetService to fetch the top gainers and losers data
        GainersAndLosersDTO gainersAndLosersDTO = assetService.getTopGainersAndLosers();

        // Check if data is retrieved successfully
        if (gainersAndLosersDTO != null) {
            // Return a success response with the fetched data
            return ResponseHandler.successResponse(HttpStatus.OK,
                    "Top gainers and losers assets fetched successfully!!!", gainersAndLosersDTO);
        }

        // Return an error response if data retrieval failed
        return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "Fetch top gainers and losers failed!!!");
    }

    // Endpoint to get news and sentiment data based on a request
    @GetMapping("/getNewsAndSentiment")
    public ResponseEntity<Object> getNewsAndSentiment(@RequestBody NewsAndSentimentRequest newsAndSentimentRequest)
            throws JsonMappingException, JsonProcessingException {
        // Call the AssetService to fetch news and sentiment data based on the provided
        // request
        NewsAndSentimentDTO newsAndSentimentDTO = assetService.getNewsAndSentiment(newsAndSentimentRequest);

        // Check if data is retrieved successfully
        if (newsAndSentimentDTO != null) {
            // Return a success response with the fetched data
            return ResponseHandler.successResponse(HttpStatus.OK, "Related news and sentiment fetched successfully",
                    newsAndSentimentDTO);
        }

        // Return an error response if data retrieval failed
        return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "Fetch news and sentiment failed!!!");
    }
}