package com.market.tracker.model.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.market.tracker.util.serializerDeserializer.NewsAndSentimentDTODeserializer;

@JsonDeserialize(using = NewsAndSentimentDTODeserializer.class)
public class NewsAndSentimentDTO {
    private String items;

    private String sentimentScoreDefinition;

    private String relevanceScoreDefinition;

    private List<FeedItemDTO> feed;

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getSentimentScoreDefinition() {
        return sentimentScoreDefinition;
    }

    public void setSentimentScoreDefinition(String sentimentScoreDefinition) {
        this.sentimentScoreDefinition = sentimentScoreDefinition;
    }

    public String getRelevanceScoreDefinition() {
        return relevanceScoreDefinition;
    }

    public void setRelevanceScoreDefinition(String relevanceScoreDefinition) {
        this.relevanceScoreDefinition = relevanceScoreDefinition;
    }

    public List<FeedItemDTO> getFeed() {
        return feed;
    }

    public void setFeed(List<FeedItemDTO> feed) {
        this.feed = feed;
    }

}
