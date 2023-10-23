package com.market.tracker.dto;

public class TickerSentimentDTO {
    private String ticker;

    private String relevanceScore;

    private String tickerSentimentScore;

    private String tickerSentimentLabel;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getRelevanceScore() {
        return relevanceScore;
    }

    public void setRelevanceScore(String relevanceScore) {
        this.relevanceScore = relevanceScore;
    }

    public String getTickerSentimentScore() {
        return tickerSentimentScore;
    }

    public void setTickerSentimentScore(String tickerSentimentScore) {
        this.tickerSentimentScore = tickerSentimentScore;
    }

    public String getTickerSentimentLabel() {
        return tickerSentimentLabel;
    }

    public void setTickerSentimentLabel(String tickerSentimentLabel) {
        this.tickerSentimentLabel = tickerSentimentLabel;
    }

}
