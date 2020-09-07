package com.Backend.DynamoAccess.DBLayer;

import com.Backend.DynamoAccess.Models.StringListModel;
import com.Backend.DynamoAccess.Utils.Utils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SuggestionsRepository {

    @Autowired
    DynamoDBMapper mapper;

    public List<String> retrieveTopics() {
        return mapper.load(StringListModel.class, Utils.TOPICS_LIST).getSuggestions();
    }

    public List<String> retrieveTodayTopics() {
        return mapper.load(StringListModel.class, Utils.TODAY_TOPICS).getSuggestions();
    }

    public void revisitTodayTopics() {
        Random rand = new Random();
        List<String> preProcess = retrieveTopics();
        List<String> postProcess = new ArrayList<>();
        for (int i = 0; i < Utils.RAND_THRESHOLD; i++) {
            int nextRand = rand.nextInt(preProcess.size());
            postProcess.add(preProcess.get(nextRand));
            preProcess.remove(nextRand);
        }
        StringListModel curr = new StringListModel(Utils.TODAY_TOPICS, postProcess);
        mapper.save(curr, buildExpression(curr));
    }

    private DynamoDBSaveExpression buildExpression(StringListModel model) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put(Utils.SUGGESTION_CATEGORY, new ExpectedAttributeValue(new AttributeValue().withS(model.getSuggestionListTitle())));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
