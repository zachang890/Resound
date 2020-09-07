package com.Backend.DynamoAccess.DBLayer;

import com.Backend.DynamoAccess.Interfaces.NewsInterface;
import com.Backend.DynamoAccess.Models.NewsDetails;
import com.Backend.DynamoAccess.Models.NewsDetailsDynamo;
import com.Backend.DynamoAccess.Utils.Utils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Repository
public class NewsDetailsRepository {

    @Autowired
    DynamoDBMapper mapper;

    @Autowired
    NewsInterface newsInterface;

    public NewsDetailsDynamo addNewsDetails(String topic) {
        List<NewsDetails> crawl = newsInterface.crawlNews(topic);
        NewsDetailsDynamo toReturn = new NewsDetailsDynamo(topic, crawl);
        mapper.save(toReturn);
        return toReturn;
    }

    public NewsDetailsDynamo findNewsDetails(String topic) {
        return mapper.load(NewsDetailsDynamo.class, topic);
    }

    public String deleteNewsDetails(NewsDetailsDynamo newsDetailsDynamo) {
        mapper.delete(newsDetailsDynamo);
        return Utils.REMOVED;
    }

    public String updateNewsDetails(String topic) {
        NewsDetailsDynamo updated = new NewsDetailsDynamo(topic, newsInterface.crawlNews(topic));
        mapper.save(updated, buildExpression(updated));
        return Utils.UPDATED;
    }

    private DynamoDBSaveExpression buildExpression(NewsDetailsDynamo newsDetailsDynamo) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put(Utils.TOPIC, new ExpectedAttributeValue(new AttributeValue().withS(newsDetailsDynamo.getTopic())));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
