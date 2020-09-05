package com.Backend.DynamoAccess.Models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Suggestions-collection")
public class StringListModel {

    @DynamoDBHashKey(attributeName = "suggestion-category")
    private String suggestionListTitle;

    @DynamoDBAttribute(attributeName = "entries")
    private List<String> suggestions;
}
