package com.market.tracker.model.dto;

import java.util.List;

public class FeedItemDTO {
    private String title;

    private String url;

    private String timePublished;

    private List<String> authors;

    private String summary;

    private String bannerImage;

    private String source;

    private String categoryWithinSource;

    private String sourceDomain;

    private List<TopicDTO> topics;

    private double overallSentimentScore;

    private String overallSentimentLabel;

    private List<TickerSentimentDTO> tickerSentiment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimePublished() {
        return timePublished;
    }

    public void setTimePublished(String timePublished) {
        this.timePublished = timePublished;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategoryWithinSource() {
        return categoryWithinSource;
    }

    public void setCategoryWithinSource(String categoryWithinSource) {
        this.categoryWithinSource = categoryWithinSource;
    }

    public String getSourceDomain() {
        return sourceDomain;
    }

    public void setSourceDomain(String sourceDomain) {
        this.sourceDomain = sourceDomain;
    }

    public List<TopicDTO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicDTO> topics) {
        this.topics = topics;
    }

    public double getOverallSentimentScore() {
        return overallSentimentScore;
    }

    public void setOverallSentimentScore(double overallSentimentScore) {
        this.overallSentimentScore = overallSentimentScore;
    }

    public String getOverallSentimentLabel() {
        return overallSentimentLabel;
    }

    public void setOverallSentimentLabel(String overallSentimentLabel) {
        this.overallSentimentLabel = overallSentimentLabel;
    }

    public List<TickerSentimentDTO> getTickerSentiment() {
        return tickerSentiment;
    }

    public void setTickerSentiment(List<TickerSentimentDTO> tickerSentiment) {
        this.tickerSentiment = tickerSentiment;
    }

}
