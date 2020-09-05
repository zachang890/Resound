package com.Backend.DynamoAccess.DBLayer;

import com.Backend.DynamoAccess.Interfaces.NewsInterface;
import com.Backend.DynamoAccess.Models.StringListModel;
import com.Backend.DynamoAccess.Utils.Utils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SuggestionsRepository {

    @Autowired
    DynamoDBMapper mapper;

    public List<String> retrieveTopics() {
        return mapper.load(StringListModel.class, Utils.TOPICS_LIST).getSuggestions();
    }
}
