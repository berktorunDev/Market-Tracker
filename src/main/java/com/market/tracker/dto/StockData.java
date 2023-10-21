package com.market.tracker.dto;

import java.util.List;
import java.util.Map;

public class StockData {
    private Map<String, String> headers;
    private List<StockItem> rows;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public List<StockItem> getRows() {
        return rows;
    }

    public void setRows(List<StockItem> rows) {
        this.rows = rows;
    }

}
