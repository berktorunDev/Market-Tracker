package com.market.tracker.util.serializerDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.market.tracker.dto.FeedItemDTO;
import com.market.tracker.dto.NewsAndSentimentDTO;
import com.market.tracker.dto.TickerSentimentDTO;
import com.market.tracker.dto.TopicDTO;

/**
 * Custom JSON deserializer for converting JSON data into a NewsAndSentimentDTO
 * object.
 */
public class NewsAndSentimentDTODeserializer extends StdDeserializer<NewsAndSentimentDTO> {
    public NewsAndSentimentDTODeserializer() {
        this(null);
    }

    public NewsAndSentimentDTODeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public NewsAndSentimentDTO deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode rootNode = jp.getCodec().readTree(jp);
        NewsAndSentimentDTO responseDTO = new NewsAndSentimentDTO();

        // Extract and set values from the JSON root node
        responseDTO.setItems(rootNode.get("items").asText());
        responseDTO.setSentimentScoreDefinition(rootNode.get("sentiment_score_definition").asText());
        responseDTO.setRelevanceScoreDefinition(rootNode.get("relevance_score_definition").asText());

        List<FeedItemDTO> feedItems = new ArrayList<>();
        JsonNode feedNode = rootNode.get("feed");

        if (feedNode.isArray()) {
            for (JsonNode itemNode : feedNode) {
                FeedItemDTO feedItem = new FeedItemDTO();

                // Extract and set values for each FeedItemDTO
                feedItem.setTitle(itemNode.get("title").asText());
                feedItem.setUrl(itemNode.get("url").asText());
                feedItem.setTimePublished(itemNode.get("time_published").asText());
                feedItem.setSummary(itemNode.get("summary").asText());
                feedItem.setBannerImage(itemNode.get("banner_image").asText());
                feedItem.setSource(itemNode.get("source").asText());
                feedItem.setCategoryWithinSource(itemNode.get("category_within_source").asText());
                feedItem.setSourceDomain(itemNode.get("source_domain").asText());

                List<TopicDTO> topics = new ArrayList<>();
                JsonNode topicsNode = itemNode.get("topics");

                if (topicsNode.isArray()) {
                    for (JsonNode topicNode : topicsNode) {
                        TopicDTO topic = new TopicDTO();
                        topic.setTopic(topicNode.get("topic").asText());
                        topic.setRelevanceScore(topicNode.get("relevance_score").asText());
                        topics.add(topic);
                    }
                }
                feedItem.setTopics(topics);

                feedItem.setOverallSentimentScore(itemNode.get("overall_sentiment_score").asDouble());
                feedItem.setOverallSentimentLabel(itemNode.get("overall_sentiment_label").asText());

                List<TickerSentimentDTO> tickerSentiments = new ArrayList<>();
                JsonNode tickerSentimentsNode = itemNode.get("ticker_sentiment");

                if (tickerSentimentsNode.isArray()) {
                    for (JsonNode tickerSentimentNode : tickerSentimentsNode) {
                        TickerSentimentDTO tickerSentiment = new TickerSentimentDTO();
                        tickerSentiment.setTicker(tickerSentimentNode.get("ticker").asText());
                        tickerSentiment.setRelevanceScore(tickerSentimentNode.get("relevance_score").asText());
                        tickerSentiment
                                .setTickerSentimentScore(tickerSentimentNode.get("ticker_sentiment_score").asText());
                        tickerSentiment
                                .setTickerSentimentLabel(tickerSentimentNode.get("ticker_sentiment_label").asText());
                        tickerSentiments.add(tickerSentiment);
                    }
                }
                feedItem.setTickerSentiment(tickerSentiments);

                feedItems.add(feedItem);
            }
        }

        // Set the list of FeedItems in the NewsAndSentimentDTO
        responseDTO.setFeed(feedItems);

        return responseDTO;
    }
}
