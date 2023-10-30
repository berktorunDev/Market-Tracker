package com.market.tracker.model.request;

public class NewsAndSentimentRequest {
    private String tickers;

    private int limit = 10;

    public String getTickers() {
        return tickers;
    }

    public void setTickers(String tickers) {
        this.tickers = tickers;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
