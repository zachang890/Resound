package com.Backend.DynamoAccess.DBLayer;

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

@Repository
public class NewsDetailsRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public NewsDetailsDynamo addNewsDetails(NewsDetailsDynamo newsDetailsDynamo) {
        mapper.save(newsDetailsDynamo);
        return newsDetailsDynamo;
    }

    public NewsDetailsDynamo findNewsDetails(String topic) {
        return mapper.load(NewsDetailsDynamo.class, topic);
    }

    public String deleteNewsDetails(NewsDetailsDynamo newsDetailsDynamo) {
        mapper.delete(newsDetailsDynamo);
        return Utils.REMOVED;
    }

    public String updateNewsDetails(NewsDetailsDynamo newsDetailsDynamo) {
        mapper.save(newsDetailsDynamo, buildExpression(newsDetailsDynamo));
        return Utils.UPDATED;
    }

    private DynamoDBSaveExpression buildExpression(NewsDetailsDynamo newsDetailsDynamo) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("topic", new ExpectedAttributeValue(new AttributeValue().withS(newsDetailsDynamo.getTopic())));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
